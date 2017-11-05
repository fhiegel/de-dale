package com.dedale.core.expression;

final class Neutral implements Expression {
    
    Neutral() {
    }

    @Override
    public Neutral evaluate() {
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