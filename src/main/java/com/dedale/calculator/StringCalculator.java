package com.dedale.calculator;

public class StringCalculator {
    
    public Integer calculate(String sentence) {
        for (StringToIntegerOperation operation : StringToIntegerOperation.values()) {
            if (operation.mayApplyOperation(sentence)) {
                return operation.apply(sentence, this::calculate);
            }
        }
        return Integer.parseInt(sentence);
    }
    
}
