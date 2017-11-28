package com.dedale.core.engine.expression;

public class TextExpression extends ValuedExpression<String> {

    public TextExpression(String value) {
        super(value);
    }

    @Override
    protected SyntaxTreeVisitor configure(SyntaxTreeVisitor syntaxTree) {
        return syntaxTree
                .when(BoldTextExpression.class, bold -> bold.wrapText(this))
                .when(Expression.class, ConcatCommand.CONCAT.left(this));
    }

}
