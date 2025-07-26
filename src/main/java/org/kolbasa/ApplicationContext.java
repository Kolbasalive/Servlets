package org.kolbasa;

import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ApplicationContext {
    private final Map<Class<?>, Object> beans = new HashMap<>();

    public ApplicationContext(Class<?>... classes) throws NoSuchMethodException {
        Reflections reflections = new Reflections("org.kolbasa");

        Set<Class<?>> configClasses = reflections.getTypesAnnotatedWith(Configuration.class);
        Set<Class<?>> controllerClasses = reflections.getTypesAnnotatedWith(Controller.class);

        for (Class<?> clazz : configClasses) {
            try {
                Object configInstance = clazz.getDeclaredConstructor().newInstance();
                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(Bean.class)) {
                        Class<?> returnType = method.getReturnType();
                        if (beans.containsKey(returnType)) continue;

                        Object[] args = Arrays.stream(method.getParameterTypes())
                                .map(param -> {
                                    Object dependency = beans.get(param);
                                    if (dependency == null)
                                        throw new RuntimeException("Бина не существует" + param);
                                    return dependency;
                                }).toArray();
                        Object bean = method.invoke(configInstance, args);
                        Object proxyBean = wrapWithLoggingProxy(bean);
                        beans.put(returnType, proxyBean);
                    }
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        for (Class<?> controllerClass : controllerClasses) {
            if (beans.containsKey(controllerClass)) continue;

            Constructor<?> constructor = controllerClass.getConstructors()[0];
            var paramType = constructor.getParameterTypes();

            Object[] args = Arrays.stream(paramType)
                    .map(param -> {
                        Object dependency = beans.get(param);
                        if (dependency == null){
                            throw new RuntimeException("Controller type not found: " + param);
                        }
                        return dependency;
                    })
                    .toArray();

            try {
                Object instance = constructor.newInstance(args);
                beans.put(controllerClass, instance);
            } catch (Exception e) {
                throw new RuntimeException("Ошибка создания компонента: " + controllerClass, e);
            }
        }
        System.out.println("123");
    }

    public <T> T getBean(Class<T> clazz) {
        return clazz.cast(beans.get(clazz));
    }

    public Object[] getBeans(){
        return beans.values().toArray();
    }

    private Object wrapWithLoggingProxy(Object proxy) {
        return Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                proxy.getClass().getInterfaces(),
                new LoggingInvocationHandler(proxy)
        );
    }
}