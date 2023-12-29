package edu.hw10.task2.Cache;

import java.lang.reflect.Proxy;

public class CacheProxy {

    private CacheProxy() {
    }

    public static Object create(Object object, Class<?> objectClass) {
        ClassLoader classLoader = objectClass.getClassLoader();
        Class<?>[] interfaces = objectClass.getInterfaces();
        CacheInvocationHandler invocationHandler = new CacheInvocationHandler(object, objectClass);

        return Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
    }

}
