package com.dedale.utils.resources;

import java.io.InputStream;

import com.dedale.utils.JsonUtils;

class JsonDeserializer implements Deserializer {

    @Override
    public <T> T deserialize(InputStream inputStream, Class<T> valueType) {
        return JsonUtils.loadAs(inputStream, valueType);
    }

}
