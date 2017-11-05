package com.dedale.calculator;

import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;

class StringToIntegerOperation {
    
    private static final String ESCAPE_PREFIX = "\\";
    private static final String REGEXP_SYMBOLS = "+-*^";
    
    private String symbol;
    private OperandReader operandReader;
    private IntegerOperation operation;
    
    StringToIntegerOperation(String symbol, OperandReader operandReader, IntegerOperation function) {
        this.symbol = symbol;
        this.operandReader = operandReader;
        this.operation = function;
    }
    
    public boolean mayApplyOperation(String statement) {
        if (StringUtils.isBlank(statement)) {
            return false;
        }
        if (REGEXP_SYMBOLS.contains(symbol)) {
            return statement.matches(".*\\d+\\s*" + ESCAPE_PREFIX + symbol + "\\s*\\d+.*");
        } else {
            return statement.matches(".*\\d+\\s*" + symbol + "\\s*\\d+.*");
        }
    }
    
    public Integer apply(String sentence, Function<String, Integer> parser) {
        String left = operandReader.readLeftOperand(sentence, symbol);
        String right = operandReader.readRightOperand(sentence, symbol);
        return operation.apply(parser.apply(left), parser.apply(right));
    }
    
}