package com.dedale.core.expression;

@FunctionalInterface
public interface CommandCombiner {
    Expression execute(Expression left, Expression right);
}