package com.dedale.calculator;

import static com.dedale.calculator.OperandReader.LEFT_TO_RIGHT;
import static com.dedale.calculator.OperandReader.RIGHT_TO_LEFT;

import java.util.function.Function;

enum StringToIntegerOperation {
    
    PLUS("+", LEFT_TO_RIGHT, (left, right) -> left + right),
    
    MINUS("-", LEFT_TO_RIGHT, (left, right) -> left - right),
    
    MULTIPLY("*", LEFT_TO_RIGHT, (left, right) -> left * right),
    
    POWER("^", RIGHT_TO_LEFT, (left, right) -> (int) Math.pow(left, right));
    
    @FunctionalInterface
    interface IntegerOperation {
        Integer apply(Integer left, Integer right);
    }
    
    private static final String ESCAPE_PREFIX = "\\";
    
    private String symbol;
    private OperandReader operandReader;
    private IntegerOperation operation;
    
    private StringToIntegerOperation(String symbol, OperandReader operandReader, IntegerOperation function) {
        this.symbol = symbol;
        this.operandReader = operandReader;
        this.operation = function;
    }
    
    public boolean mayApplyOperation(String appliyableString) {
        if (appliyableString == null || appliyableString.isEmpty()) {
            return false;
        }
        return appliyableString.matches(".+" + ESCAPE_PREFIX + symbol + ".+");
    }
    
    public Integer apply(String sentence, Function<String, Integer> parser) {
        String left = operandReader.readLeftOperand(sentence, symbol);
        String right = operandReader.readRightOperand(sentence, symbol);
        return operation.apply(parser.apply(left), parser.apply(right));
    }
    
}