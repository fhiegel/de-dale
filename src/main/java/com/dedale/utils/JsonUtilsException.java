package com.dedale.utils;

import com.dedale.utils.exceptions.TechnicalException;

class JsonUtilsException extends TechnicalException {

    private static final long serialVersionUID = 1L;
    
    public JsonUtilsException(String message, Throwable e) {
        super(message, e);
    }

}
