package com.dedale.utils.resources;

class ResourceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    ResourceException(String message, Throwable e) {
        super(message, e);
    }

}
