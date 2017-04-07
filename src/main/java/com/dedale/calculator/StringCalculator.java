package com.dedale.calculator;

import static com.dedale.calculator.OperandReader.LEFT_TO_RIGHT;
import static com.dedale.calculator.OperandReader.RIGHT_TO_LEFT;
import static java.lang.Math.pow;

import java.util.Collection;

public class StringCalculator {
    
    private Collection<StringToIntegerOperation> operations;
    
    public StringCalculator() {
        this(beginBuildOperations().build());
    }
    
    public StringCalculator(Collection<StringToIntegerOperation> operations) {
        this.operations = operations;
    }
    
    static OperationListBuilder beginBuildOperations() {
        return new OperationListBuilder()
                
                .addOperation()
                .withSymbol("+")
                .withOperandReader(LEFT_TO_RIGHT)
                .withOperation((left, right) -> left + right)
                .endOperation()
                
                .addOperation()
                .withSymbol("-")
                .withOperandReader(LEFT_TO_RIGHT)
                .withOperation((left, right) -> left - right)
                .endOperation()
                
                .addOperation()
                .withSymbol("*")
                .withOperandReader(LEFT_TO_RIGHT)
                .withOperation((left, right) -> left * right)
                .endOperation()
                
                .addOperation()
                .withSymbol("^")
                .withOperandReader(RIGHT_TO_LEFT)
                .withOperation((left, right) -> (int) pow(left, right))
                .endOperation()
                
                .addOperation()
                .withSymbol("d")
                .withOperandReader(LEFT_TO_RIGHT)
                .withOperation(SumDiceRollsOperation::sumDices)
                .endOperation();
    }
    
    public Integer calculate(String sentence) {
        for (StringToIntegerOperation operation : operations) {
            if (operation.mayApplyOperation(sentence)) {
                return operation.apply(sentence, this::calculate);
            }
        }
        return Integer.parseInt(sentence);
    }
    
}
