package org.kolbasa;

import java.util.ArrayList;
import java.util.List;

public class RouterBuilder {
    private RouterBuilder() {}

    public static List<RouterKey> build() {
        List<RouterKey> routerKeys = new ArrayList<>();
        RouterKey routerKeyGet = new RouterKey("GET", "/support");
        RouterKey routerKeyPost = new RouterKey("POST", "/support");
        routerKeys.add(routerKeyGet);
        routerKeys.add(routerKeyPost);

        return routerKeys;
    }
}
