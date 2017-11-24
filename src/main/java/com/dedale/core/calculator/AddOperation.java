package com.dedale.core.calculator;

import com.dedale.core.engine.expression.Expression;

public class AddOperation extends AbstractArithmeticOperation {

    private static final IntegerFunction ADD = (left, right) -> left + right;

    public static final AddOperation EMPTY = new AddOperation();

    private AddOperation() {
        super(ADD);
    }

    private AddOperation(Expression left, Expression right) {
        super(ADD, left, right);
    }

    @Override
    protected ArithmeticExpression copy(Expression left, Expression right) {
        return new AddOperation(left, right);
    }

}
