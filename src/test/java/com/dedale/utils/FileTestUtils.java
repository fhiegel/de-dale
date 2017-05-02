package com.dedale.utils;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class FileTestUtils {

    public static String getResourceFileAsString(Class<?> seedClass, String filePath) {
        try {
            File file = getResourceFile(seedClass, filePath);
            FileInputStream input = new FileInputStream(file);
            return IOUtils.toString(input, UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Cannot read resources file. seedClass:\"" + seedClass + "\", file:\"" + filePath + "\"", e);
        }
    }

    public static String getResourceFileAsJson(Class<?> seedClass, String filePath) {
        File file = getResourceFile(seedClass, filePath);
        return JsonUtils.asJson(file);
    }

    public static File getResourceFile(Class<?> seedClass, String filePath) {
        URL resource = seedClass.getResource("");
        return new File(resource.getPath(), filePath);
    }

}
