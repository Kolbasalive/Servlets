package org.kolbasa;

import java.lang.reflect.Method;

public class HandlerMethod {
    private final Object controller; //бин
    private final Method method;     //метод

    public HandlerMethod(Object controller, Method method) {
        this.controller = controller;
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public Method getMethod() {
        return method;
    }
}
