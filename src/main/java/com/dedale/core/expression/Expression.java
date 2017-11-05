package com.dedale.core.expression;

public interface Expression {

    Expression NEUTRAL = new Neutral();

    Expression then(Expression after);

    Expression evaluate();

    default <R> void accept(ExpressionVisitor<R> visitor) {
        visitor.visit(this);
    }

}
