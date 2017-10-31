package com.dedale.core.expression;

@FunctionalInterface
public interface Command {
    Expression execute(Expression left, Expression right);
}