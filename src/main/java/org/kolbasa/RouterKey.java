package org.kolbasa;

import java.util.Objects;

public class RouterKey {
    private String method;
    private String path;

    public RouterKey(String method, String path) {
        this.method = method;
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RouterKey)) return false;
        RouterKey that = (RouterKey) o;
        return Objects.equals(method, that.method) && Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, path);
    }
}
