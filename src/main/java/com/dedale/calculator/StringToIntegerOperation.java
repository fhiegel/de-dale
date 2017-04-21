package com.dedale.calculator;

import java.util.function.Function;

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
    
    public boolean mayApplyOperation(String appliyableString) {
        if (appliyableString == null || appliyableString.isEmpty()) {
            return false;
        }
        if (REGEXP_SYMBOLS.contains(symbol)) {
            return appliyableString.matches("\\d+" + ESCAPE_PREFIX + symbol + "\\d+");
        } else {
            return appliyableString.matches("\\d+" + symbol + "\\d+");
        }
    }
    
    public Integer apply(String sentence, Function<String, Integer> parser) {
        String left = operandReader.readLeftOperand(sentence, symbol);
        String right = operandReader.readRightOperand(sentence, symbol);
        return operation.apply(parser.apply(left), parser.apply(right));
    }
    
}