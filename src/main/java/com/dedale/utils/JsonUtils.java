package com.dedale.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.OptionalDouble;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {
    
    static final ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
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
            JsonNode tree = objectMapper.readTree(file);
            return asJson(tree);
        } catch (IOException e) {
            throw new JsonUtilsException("Cannot write file as JSON : " + file.getPath(), e);
        }
    }
    
    public static <T> String asJson(InputStream inputStream, Class<T> valueType) {
        try {
            T value = objectMapper.readValue(inputStream, valueType);
            return asJson(value);
        } catch (IOException e) {
            throw new JsonUtilsException("Cannot write inputStream as JSON for class : input=" + inputStream + ", class=" + valueType, e);
        }
    }
    
    public static void writeValue(OutputStream out, Object value) {
        try {
            objectMapper.writeValue(out, value);
        } catch (IOException e) {
            throw new JsonUtilsException("Cannot write value as JSON in given output : value=" + value + ", out=" + out, e);
        }
        
    }
    
}
