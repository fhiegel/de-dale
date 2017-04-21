package com.dedale.calculator;

@FunctionalInterface
interface IntegerOperation {
    Integer apply(Integer left, Integer right);
}