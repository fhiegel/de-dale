package com.dedale.core.expression;

@FunctionalInterface
public interface IntegerFunction {
    Integer apply(Integer left, Integer right);
}