package com.dedale.app;

public class CoreResult {

    private final String value;

    CoreResult(String value) {
        this.value = value;
    }

    public static CoreResult empty() {
        return new CoreResult("empty");
    }

    @Override
    public String toString() {
        return value;
    }

    public CoreResult mergeWith(CoreResult appended) {
        return appended;
    }
}
