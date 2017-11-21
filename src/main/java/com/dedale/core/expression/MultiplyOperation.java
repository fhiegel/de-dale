package com.dedale.core.expression;

public class MultiplyOperation extends AbstractArithmeticOperation {
    
    private static final IntegerFunction MULTIPLY = (left, right) -> left * right;

    public static final MultiplyOperation EMPTY = new MultiplyOperation();

    private MultiplyOperation() {
        super(MULTIPLY);
    }

    private MultiplyOperation(Expression left, Expression right) {
        super(MULTIPLY, left, right);
    }

    @Override
    protected
    ArithmeticExpression copy(Expression left, Expression right) {
        return new MultiplyOperation(left, right);
    }

}
