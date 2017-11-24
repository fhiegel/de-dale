package com.dedale.core.calculator;

@FunctionalInterface
public interface IntegerFunction {
    Integer apply(Integer left, Integer right);
}