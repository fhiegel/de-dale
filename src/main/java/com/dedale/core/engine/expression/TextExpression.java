package com.dedale.core.engine.expression;

public class TextExpression extends ValuedExpression<String> {

    public TextExpression(String value) {
        super(value);
    }

    @Override
    protected ExpressionVisitor<Expression> configure(ExpressionVisitor<Expression> dispatcher) {
        return dispatcher
                .when(BoldTextExpression.class, bold -> bold.wrapText(this))
                .when(Expression.class, ConcatCommand.CONCAT.left(this));
    }

}
