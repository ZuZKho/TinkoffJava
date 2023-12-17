package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomObjectGenerator {

    private final Random random = new Random();

    @SuppressWarnings("MagicNumber")
    private String getRandomString() {
        if (random.nextInt() % 3 == 0) {
            return null;
        }

        int len = random.nextInt(10) + 1;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < len; i++) {
            res.append((char) (random.nextInt(26) + 'a'));
        }
        return res.toString();
    }

    private Object[] getRandomParams(Class<?>[] paramsClasses, Annotation[][] paramsAnnotations) {
        Object[] params = new Object[paramsClasses.length];

        for (int i = 0; i < params.length; i++) {
            if (paramsClasses[i] == int.class) {
                // int
                int maxValue = Integer.MAX_VALUE;
                int minValue = Integer.MIN_VALUE;
                for (Annotation annotation : paramsAnnotations[i]) {
                    if (annotation instanceof Min anno) {
                        minValue = anno.value();
                    }
                    if (annotation instanceof Max anno) {
                        maxValue = anno.value();
                    }
                }

                params[i] = (int) (random.nextLong((long) maxValue - minValue) + (long) minValue);
            } else if (paramsClasses[i] == String.class) {
                // String
                String value = getRandomString();
                for (Annotation annotation : paramsAnnotations[i]) {
                    if (annotation instanceof NotNull) {
                        while (value == null) {
                            value = getRandomString();
                        }
                    }
                }
                params[i] = value;
            } else {
                throw new UnsupportedOperationException("Not supported constructor type :(");
            }
        }
        return params;
    }

    Object nextObject(Class<?> objectClass, String fabricMethod)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var methods = objectClass.getDeclaredMethods();
        List<Method> methodsList =
            Arrays.stream(methods).filter(method -> method.getName().equals(fabricMethod)).toList();
        if (methodsList.isEmpty()) {
            throw new NoSuchMethodException();
        }

        Method method = methodsList.get(random.nextInt(methodsList.size()));
        var paramsClasses = method.getParameterTypes();
        var paramsAnnotations = method.getParameterAnnotations();

        return method.invoke(null, getRandomParams(paramsClasses, paramsAnnotations));
    }

    Object nextObject(Class<?> objectClass)
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var constructors = objectClass.getDeclaredConstructors();

        Constructor<?> constructor = constructors[random.nextInt(constructors.length)];
        var paramsClasses = constructor.getParameterTypes();
        var paramsAnnotations = constructor.getParameterAnnotations();

        return constructor.newInstance(getRandomParams(paramsClasses, paramsAnnotations));
    }
}
