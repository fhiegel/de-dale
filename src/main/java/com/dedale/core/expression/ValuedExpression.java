package com.dedale.core.expression;

import java.util.Objects;

import com.dedale.core.ExecutionContext;

public abstract class ValuedExpression<T> extends AbstractExpression {

    private final T value;

    public ValuedExpression(T value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public ValuedExpression<T> execute(ExecutionContext context) {
        return this;
    }

    public final T value() {
        return value;
    }

}
