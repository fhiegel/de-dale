package com.dedale.utils.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Resources {

    static final Path ROOT = Paths.get("src", "main", "resources");

    private Resources() {
    }

    private static Resource fromRelativePath(Path relativePath) {
        return new Resource(ROOT.resolve(relativePath));
    }

    public static Resource getRelativeTo(Class<?> clazz, String relativePath) {
        try {
            URI relativeURI = clazz.getResource(relativePath).toURI();
            return fromRelativePath(Paths.get(relativeURI));
        } catch (URISyntaxException e) {
            throw new RuntimeException("fuk");
        }
    }

}
