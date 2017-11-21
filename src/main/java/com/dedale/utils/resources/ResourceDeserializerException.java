package com.dedale.utils.resources;

import com.dedale.utils.exceptions.TechnicalException;

public class ResourceDeserializerException extends TechnicalException {

    private static final long serialVersionUID = 1L;

    public ResourceDeserializerException(String message, Throwable e) {
        super(message, e);
    }

}
