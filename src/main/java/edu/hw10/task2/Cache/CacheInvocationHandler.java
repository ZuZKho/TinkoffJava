package edu.hw10.task2.Cache;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

// В Persist Mode аргументы и результаты могут быть только String
class CacheInvocationHandler implements InvocationHandler {

    private final Object object;
    private final Class<?> objectClass;
    private final HashMap<Method, HashMap<Object[], Object>> cachedCalls = new HashMap<>();

    CacheInvocationHandler(Object object, Class<?> objectClass) {
        this.object = object;
        this.objectClass = objectClass;
    }

    private Object processNoPersistentMethod(Method method, Object[] args)
        throws InvocationTargetException, IllegalAccessException {
        if (cachedCalls.containsKey(method)) {
            var methodCachedCalls = cachedCalls.get(method);

            for (var entry : methodCachedCalls.entrySet()) {
                int i = 0;
                boolean flag = true;
                for (var key : entry.getKey()) {
                    if (!key.equals(args[i])) {
                        flag = false;
                        break;
                    }
                    i++;
                }
                if (flag) {
                    return entry.getValue();
                }
            }
        }

        Object result = method.invoke(object, args);
        if (!cachedCalls.containsKey(method)) {
            cachedCalls.put(method, new HashMap<>());
        }
        cachedCalls.get(method).put(args, result);
        return result;
    }

    private Object processPersistentMethod(Method method, Object[] args)
        throws IOException, InvocationTargetException, IllegalAccessException {
        File file = new File("src/main/resources/cache/" + method.getName());
        file.getParentFile().mkdirs();
        file.createNewFile();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        // Смотрим был ли вызван метод с такими аргументами
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }

            String[] words = line.split(" ");
            if (words.length - 1 == args.length) {
                boolean flag = true;
                for (int i = 0; i < words.length - 1; i++) {
                    if (!words[i].equals(args[i])) {
                        flag = false;
                    }
                }
                if (flag) {
                    return words[words.length - 1];
                }
            }
        }
        reader.close();

        // Если не был, то вызываем.
        Object result = method.invoke(object, args);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        String toWrite = "";
        for (var arg : args) {
            toWrite += (String) arg + " ";
        }
        toWrite += (String) result + "\n";
        writer.write(toWrite);
        writer.close();

        return result;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws InvocationTargetException, IllegalAccessException, IOException {
        Boolean cachePersistence = null;

        var annotations = method.getAnnotations();
        var cacheAnnotationArray =
            Arrays.stream(annotations).filter(annotation -> annotation instanceof Cache).toArray();
        if (cacheAnnotationArray.length == 1) {
            if (cacheAnnotationArray[0] instanceof Cache annotation) {
                cachePersistence = annotation.persist();
            }
        }

        if (cachePersistence != null && cachePersistence) {
            // Only for string arguments and string return values
            return processPersistentMethod(method, args);
        } else if (cachePersistence != null && !cachePersistence) {
            // Любые аргументы и любой return value
            return processNoPersistentMethod(method, args);
        }

        return method.invoke(object, args);
    }
}
