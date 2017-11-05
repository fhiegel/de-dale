package com.dedale.utils.resources;

import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;

class YamlDeserializer implements Deserializer {
    @Override
    public <T> T deserialize(InputStream inputStream, Class<T> valueType) {
        return new Yaml().loadAs(inputStream, valueType);
    }
}