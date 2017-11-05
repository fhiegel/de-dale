package com.dedale.utils.resources;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;

import org.apache.commons.io.IOUtils;

public class Resource {

    private Class<?> seedClass;
    private Path resourcePath;

    public Resource(Class<?> clazz, Path resourcePath) {
        this.seedClass = clazz;
        this.resourcePath = resourcePath;
    }

    public Resource(Path resourcePath) {
        this.resourcePath = resourcePath;
    }

    public ResourceDeserializer fromYaml() {
        return new ResourceDeserializer(inputStream(), new YamlDeserializer());
    }

    private Supplier<InputStream> inputStream() {
        if (seedClass == null) {
            return () -> readAbsolutePath(resourcePath);
        }
        return () -> readRelativePath(seedClass, resourcePath);
    }

    public ResourceDeserializer fromJson() {
        return new ResourceDeserializer(inputStream(), new JsonDeserializer());
    }

    public String asString() {
        try {
            return IOUtils.toString(inputStream().get(), UTF_8);
        } catch (IOException e) {
            throw new ResourceDeserializerException("Cannot read resources file.", e);
        }
    }

    public File asFile() {
        URL resource = seedClass.getResource("");
        File classPackageDirectory = new File(resource.getPath());
        return new File(classPackageDirectory, resourcePath.toString());
    }

    private static InputStream readRelativePath(Class<?> seedClass, Path resourcePath) {
        try {
            return seedClass.getResourceAsStream(resourcePath.toString());
        } catch (Exception e) {
            throw new ResourceDeserializerException(
                    "Cannot read resources file. seedClass:\"" + seedClass + "\", resourcePath:\"" + resourcePath + "\"", e);
        }
    }

    private static InputStream readAbsolutePath(Path resourcePath) {
        try {
            return Files.newInputStream(resourcePath);
        } catch (Exception e) {
            throw new ResourceDeserializerException("Cannot read resources file.", e);
        }
    }

}