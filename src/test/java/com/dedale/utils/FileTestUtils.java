package com.dedale.utils;

import java.io.File;

import com.dedale.utils.resources.Resources;

public class FileTestUtils {

    public static String getResourceFileAsString(Class<?> seedClass, String filePath) {
        return Resources.getRelativeTo(seedClass, filePath).asString();
    }

    public static String getResourceFileAsJson(Class<?> seedClass, String filePath) {
        File file = getResourceFile(seedClass, filePath);
        return JsonUtils.asJson(file);
    }

    private static File getResourceFile(Class<?> seedClass, String filePath) {
        return Resources.getRelativeTo(seedClass, filePath).asFile();
    }

}
