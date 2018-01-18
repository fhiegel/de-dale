package com.dedale.core.engine.expression;

public class SyntaxTreeVisitor extends ExpressionVisitor<Expression> {

    private final Expression origin;
    
    public SyntaxTreeVisitor(Expression origin) {
        super(origin);
        this.origin = origin;
        this.defaultEvaluation = expression -> new TextExpression(unsupportedExpressionMessage(expression));
    }

    private String unsupportedExpressionMessage(Expression expression) {
        return "Origin " + origin.getClass().getName() + " cannot handle Expression : expression=" + expression + ", expressionClass="
                + expression.getClass().getName() + ", supported=" + callbacks.keySet();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected SyntaxTreeVisitor visitor(Expression result) {
        return  new SyntaxTreeVisitor(result);
    }
    
    public Expression next() {
        return result;
    }

}
