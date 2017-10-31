package com.dedale.core.expression;

import static java.lang.Math.pow;

public class PowerOperation extends AbstractArithmeticOperation {

    private static final IntegerFunction POW = (left, right) -> (int) pow(left, right);
    public static final PowerOperation EMPTY = new PowerOperation();

    private PowerOperation() {
        super(POW);
    }

    private PowerOperation(Expression left, Expression right) {
        super(POW, left, right);
    }

    @Override
    protected ArithmeticExpression copy(Expression left, Expression right) {
        return new PowerOperation(left, right);
    }

    @Override
    protected ExpressionVisitor configure(ExpressionVisitor dispatcher) {
        return super.configure(dispatcher)
                .when(PowerOperation.class, pow -> pow.assignLeft(this))
                .when(MultiplyOperation.class, multiply -> multiply.assignLeft(this));
    }

    @Override
    public String print() {
        return printDelegate(left) + "^" + printDelegate(right);
    }

}
