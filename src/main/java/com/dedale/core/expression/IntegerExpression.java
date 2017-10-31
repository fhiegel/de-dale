package com.dedale.core.expression;

public class IntegerExpression extends ValuedExpression<Integer> implements ArithmeticExpression {

    public IntegerExpression(Integer value) {
        super(value);
    }

    @Override
    protected ExpressionVisitor configure(ExpressionVisitor dispatcher) {
        return dispatcher
                .when(AbstractArithmeticOperation.class, operation -> operation.assignLeft(this))
                .when(Expression.class, e -> ConcatCommand.concat(this, e));
    }

}
