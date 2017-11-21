package com.dedale.utils.resources;

import java.io.InputStream;

interface Deserializer {
    <T> T deserialize(InputStream inputStream, Class<T> valueType);
}