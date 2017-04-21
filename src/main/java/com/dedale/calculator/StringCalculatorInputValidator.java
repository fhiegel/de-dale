package com.dedale.calculator;

import javax.inject.Inject;

public class StringCalculatorInputValidator {
    
    private StringCalculator stringCalculator;
    
    @Inject
    public StringCalculatorInputValidator(StringCalculator stringCalculator) {
        this.stringCalculator = stringCalculator;
    }
    
    public boolean validate(String input) {
        return stringCalculator.getOperations().stream().anyMatch(o -> o.mayApplyOperation(input)) || input.matches("\\d+");
    }
    
}
