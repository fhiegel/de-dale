package com.dedale.calculator;

import java.util.ArrayList;
import java.util.Collection;

class OperationListBuilder {
    
    private Collection<StringToIntegerOperation> operations = new ArrayList<>();
    
    public OperationBuilder<OperationListBuilder> addOperation() {
        return new OperationBuilder<>(operations::add, this);
    }
    
    public Collection<StringToIntegerOperation> build() {
        return operations;
    }
}
