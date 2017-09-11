package com.dedale.calculator;

@FunctionalInterface
public interface IntegerOperation {
    Integer apply(Integer left, Integer right);
}