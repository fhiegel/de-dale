package com.dedale.utils.resources;

import com.dedale.utils.JsonUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class Resource {

    private Path resourcePath;

    Resource(Path resourcePath) {
        this.resourcePath = resourcePath;
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
            throw new ResourceException("Cannot read resources file.", e);
        }
    }

    public String asJson() {
        return JsonUtils.asJson(asFile());
    }

    public File asFile() {
        return resourcePath.toFile();
    }

}