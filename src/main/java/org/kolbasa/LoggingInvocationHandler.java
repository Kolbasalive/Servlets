package org.kolbasa;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggingInvocationHandler implements InvocationHandler {
    private final Object target;

    public LoggingInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Logged.class)) {
            System.out.println(">> Метод: " + method.getName());
            if (args != null) {
                for (Object arg : args) {
                    System.out.println(">> Аргумент: " + arg);
                }
            }
            Object result = method.invoke(target, args);
            System.out.println("<< Результат: " + result);

            return result;
        } else
            return method.invoke(target, args);
    }
}