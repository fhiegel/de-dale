package com.dedale.utils;

import org.mockito.internal.util.reflection.Fields;
import org.mockito.internal.util.reflection.InstanceField;

public final class Whitebox {
    
    private Whitebox() {
    }
    
    public static Object getInternalState(Object target, String field) {
        InstanceField instanceField = findInstanceField(target, field);
        return instanceField.read();
    }
    
    public static void setInternalState(Object target, String field, Object value) {
        InstanceField instanceField = findInstanceField(target, field);
        instanceField.set(value);
    }
    
    private static InstanceField findInstanceField(Object target, String field) {
        return Fields
                .allDeclaredFieldsOf(target)
                .instanceFields()
                .stream()
                .filter(_instanceField -> field.equals(_instanceField.name()))
                .findFirst()
                .get();
    }
    
}
