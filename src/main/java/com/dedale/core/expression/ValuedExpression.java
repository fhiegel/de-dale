package com.dedale.core.expression;

import java.util.Objects;

public abstract class ValuedExpression<T> extends AbstractExpression {

    private final T value;

    public ValuedExpression(T value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public ValuedExpression<T> evaluate() {
        return this;
    }

    protected final T value() {
        return value;
    }

    @Override
    public String print() {
        return String.valueOf(value);
    }

}
