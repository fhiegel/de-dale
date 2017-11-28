package com.dedale.core.engine.expression;

import com.dedale.core.engine.ExecutionContext;

public class BoldTextExpression extends AbstractExpression {

    public static final BoldTextExpression EMPTY = new BoldTextExpression(null);

    private Expression text;

    private BoldTextExpression(Expression text) {
        this.text = text;
    }

    @Override
    public Expression execute(ExecutionContext context) {
        return wrapText((Expression) text.execute(context));
    }

    @Override
    protected SyntaxTreeVisitor configure(SyntaxTreeVisitor syntaxTree) {
        return super.configure(syntaxTree).when(TextExpression.class, this::wrapText);
    }

    Expression wrapText(Expression text) {
        return new BoldTextExpression(text);
    }

    @Override
    public <R> void accept(ExpressionVisitor<R> visitor) {
        super.accept(visitor);
        if (text != null) text.accept(visitor);
        super.accept(visitor);
    }

}
