package com.dedale.calculator;

import static com.dedale.calculator.OperandReader.LEFT_TO_RIGHT;
import static com.dedale.calculator.OperandReader.RIGHT_TO_LEFT;
import static java.lang.Math.pow;

import java.util.function.Function;

public class ArithmeticOperations {

    public static final StringToIntegerOperation ADD = addOperation().withSymbol("+").withOperandReader(LEFT_TO_RIGHT)
            .withOperation((left, right) -> left + right).endOperation();

    public static final StringToIntegerOperation MINUS = addOperation().withSymbol("-").withOperandReader(LEFT_TO_RIGHT)
            .withOperation((left, right) -> left - right).endOperation();

    public static final StringToIntegerOperation MULTIPLY = addOperation().withSymbol("*")
            .withOperandReader(LEFT_TO_RIGHT).withOperation((left, right) -> left * right).endOperation();

    public static final StringToIntegerOperation POWER = addOperation().withSymbol("^").withOperandReader(RIGHT_TO_LEFT)
            .withOperation((left, right) -> (int) pow(left, right)).endOperation();

    private static final OperationBuilder<StringToIntegerOperation> addOperation() {
        return new OperationBuilder<>(Function.identity());
    }

}
