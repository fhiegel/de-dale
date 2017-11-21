package com.dedale.utils.resources;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Resources {

    static final Path ROOT = Paths.get("src", "main", "resources");

    private Resources() {
    };

    public static Resource get(String first, String... more) {
        Path relativePath = Paths.get(first, more);
        return new Resource(ROOT.resolve(relativePath));
    }

    public static Resource getRelativeTo(Class<?> clazz, String relativePath) {
        return new Resource(clazz, Paths.get(relativePath));
    }

}
