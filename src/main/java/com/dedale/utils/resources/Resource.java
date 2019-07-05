package com.dedale.utils.resources;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Resource {

    private Path resourcePath;

    public Resource(Path resourcePath) {
        this.resourcePath = resourcePath;
    }

    public ResourceDeserializer fromYaml() {
        return new ResourceDeserializer(inputStream(), new YamlDeserializer());
    }

    public ResourceDeserializer fromJson() {
        return new ResourceDeserializer(inputStream(), new JsonDeserializer());
    }

    private Supplier<InputStream> inputStream() {
        return () -> readAbsolutePath(resourcePath);
    }

    public Path asPath() {
        return resourcePath;
    }

    public String asString() {
        try {
            return Files.readAllLines(resourcePath)
                    .stream()
                    .collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new ResourceDeserializerException("Cannot read resources file.", e);
        }
    }

    public File asFile() {
        return resourcePath.toFile();
    }

    private static InputStream readAbsolutePath(Path resourcePath) {
        try {
            return Files.newInputStream(resourcePath);
        } catch (Exception e) {
            throw new ResourceDeserializerException("Cannot read resources file.", e);
        }
    }

}