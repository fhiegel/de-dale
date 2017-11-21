package com.dedale.core.expression;

import com.dedale.core.ExecutionContext;

final class Neutral implements Expression {
    
    Neutral() {
    }

    @Override
    public Neutral execute(ExecutionContext context) {
        return this;
    }

    @Override
    public Expression then(Expression after) {
        return after;
    }

    @Override
    public String toString() {
        return "<neutral>";
    }

}