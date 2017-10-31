package com.dedale.core.expression;

public class TextExpression extends ValuedExpression<String> {

    public TextExpression(String value) {
        super(value);
    }

    @Override
    protected ExpressionVisitor configure(ExpressionVisitor dispatcher) {
        return dispatcher
                .when(BoldTextExpression.class, bold -> bold.wrapText(this))
                .when(Expression.class, e->ConcatCommand.concat(this, e));
    }

}
