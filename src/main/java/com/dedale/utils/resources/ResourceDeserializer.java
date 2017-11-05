package com.dedale.utils.resources;

import java.io.InputStream;
import java.util.function.Supplier;

public class ResourceDeserializer {
    private final Supplier<InputStream> inputStreamProvider;
    private final Deserializer deserializer;

    public ResourceDeserializer(Supplier<InputStream> inputStreamProvider, Deserializer serializer) {
        this.inputStreamProvider = inputStreamProvider;
        this.deserializer = serializer;
    }

    public <T> T as(Class<T> valueType) {
        return deserializer.deserialize(inputStreamProvider.get(), valueType);
    }

}