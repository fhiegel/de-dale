package com.dedale.utils;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JsonConfiguration implements ContextResolver<ObjectMapper> {
    
    @Override
    public ObjectMapper getContext(Class<?> arg0) {
        return JsonUtils.objectMapper;
    }
    
}