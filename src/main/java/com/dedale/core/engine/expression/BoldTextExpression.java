package com.dedale.core.engine.expression;

import com.dedale.core.engine.ExecutionContext;

public class BoldTextExpression extends AbstractExpression {

    public static final BoldTextExpression BOLD = new BoldTextExpression(NEUTRAL);

    private final Expression text;

    private BoldTextExpression(Expression text) {
        this.text = text;
    }

    @Override
    public Expression execute(ExecutionContext context) {
        return wrapText(text.execute(context));
    }

    @Override
    protected SyntaxTreeVisitor configure(SyntaxTreeVisitor syntaxTree) {
        return super.configure(syntaxTree).when(TextExpression.class, this::wrapText);
    }

    Expression wrapText(Expression text) {
        return new BoldTextExpression(text);
    }

    @Override
    public <R, V extends ExpressionVisitor<R>> V accept(V visitor) {
        V actual = visitor;
        actual = super.accept(visitor);
        actual = text != null ? text.accept(actual) : actual;
        actual = super.accept(visitor);
        return actual;
    }

}
