package com.dedale.core.expression;

public class MinusOperation extends AbstractArithmeticOperation {
    
    private static final IntegerFunction MINUS = (left, right) -> left - right;

    public static final MinusOperation EMPTY = new MinusOperation();

    private MinusOperation() {
        super(MINUS);
    }

    private MinusOperation(Expression left, Expression right) {
        super(MINUS, left, right);
    }

    @Override
    protected
    ArithmeticExpression copy(Expression left, Expression right) {
        return new MinusOperation(left, right);
    }

    @Override
    public String print() {
        return printDelegate(left) + "-" + printDelegate(right);
    }

}
