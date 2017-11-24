package com.dedale.core.engine.expression;

@FunctionalInterface
public interface CommandCombiner {
    Expression execute(Expression left, Expression right);
}