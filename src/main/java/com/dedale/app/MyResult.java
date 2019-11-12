package com.dedale.app;

public class MyResult {

    private final String value;

    MyResult(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
