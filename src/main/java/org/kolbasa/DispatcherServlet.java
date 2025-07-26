package org.kolbasa;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.reflections.Reflections;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DispatcherServlet extends HttpServlet {
    private final Map<RouterKey, HandlerMethod> routes = new HashMap<>();
    private ApplicationContext applicationContext;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            this.applicationContext = new ApplicationContext();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        initsRouter();
    }

    public DispatcherServlet() {
    }

    private void initsRouter() {
        Reflections reflections = new Reflections("org.kolbasa");

        Set<Class<?>> controllers = reflections.getTypesAnnotatedWith(Controller.class);
        for (Class<?> controller : controllers) {
            Method[] methods = controller.getMethods();
            List<Method> mappings = Arrays.stream(methods)
                    .filter(method -> method.isAnnotationPresent(GetMapping.class) ||
                            method.isAnnotationPresent(PostMapping.class))
                    .toList();

            for (Method method : mappings) {
                RouterKey routerKey = null;
                if (method.getAnnotation(GetMapping.class) != null) {
                    routerKey = new RouterKey("GET", method.getAnnotation(GetMapping.class).value());
                } else if (method.getAnnotation(PostMapping.class) != null) {
                    routerKey = new RouterKey("POST", method.getAnnotation(PostMapping.class).value());
                }
                Object controllerInstance = applicationContext.getBean(controller);
                HandlerMethod handlerMethod = new HandlerMethod(controllerInstance, method);
                assert routerKey != null;
                System.out.println("service!" + routerKey.getPath() + ":" + routerKey.getMethod());
                routes.put(routerKey, handlerMethod);
            }
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getRequestURI();
        String method = req.getMethod();
        RouterKey routerKey = new RouterKey(method, path);
        HandlerMethod handlerMethod = routes.get(routerKey);
        if (handlerMethod == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("Router not found");
            return;
        }

        try {
            Object controller = handlerMethod.getController();
            Method methodController = handlerMethod.getMethod();
            Object result = methodController.invoke(controller, req, resp);
            if (result != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(result);
                resp.setContentType("application/json");
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(json);
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error invoking method: " + e.getMessage());
            e.printStackTrace();
        }
    }
}