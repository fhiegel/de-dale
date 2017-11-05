package com.dedale.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {

    static final ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
    }

    public static String asJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonUtilsException("Cannot deserialize object : " + object, e);
        }
    }

    public static String asJson(File file) {
        try {
            JsonNode readTree = objectMapper.readTree(file);
            return asJson(readTree);
        } catch (IOException e) {
            throw new JsonUtilsException("Cannot write file as JSON : " + file.getPath(), e);
        }
    }

    public static <T> String asJson(InputStream inputStream, Class<T> valueType) {
        try {
            T value = loadAs(inputStream, valueType);
            return asJson(value);
        } catch (JsonUtilsException e) {
            throw new JsonUtilsException("Cannot write inputStream as JSON for class : input=" + inputStream + ", class=" + valueType, e);
        }
    }

    public static <T> T loadAs(InputStream inputStream, Class<T> valueType) {
        try {
            return objectMapper.readValue(inputStream, valueType);
        } catch (IOException e) {
            throw new JsonUtilsException("Cannot read inputStream as JSON for class : input=" + inputStream + ", class=" + valueType, e);
        }
    }

}
