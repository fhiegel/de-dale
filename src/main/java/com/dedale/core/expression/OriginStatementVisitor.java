package com.dedale.core.expression;

class OriginStatementVisitor extends ExpressionVisitor {

    private final Expression origin;

    public OriginStatementVisitor(Expression origin) {
        this.origin = origin;
        this.defaultEvaluation = expression -> new TextExpression(unsupportedExpressionMessage(expression));
    }

    private String unsupportedExpressionMessage(Expression expression) {
        return "Origin " + origin.getClass().getName() + " cannot handle Expression : expression=" + expression + ", expressionClass="
                + expression.getClass().getName() + ", supported=" + expressionToEvaluation.keySet();
    }

}
