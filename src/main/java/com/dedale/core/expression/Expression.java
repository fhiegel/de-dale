package com.dedale.core.expression;

import com.dedale.core.ExecutionContext;

public interface Expression {

    Expression NEUTRAL = new Neutral();

    Expression then(Expression after);

    Expression execute(ExecutionContext context);

    default <R> void accept(ExpressionVisitor<R> visitor) {
        visitor.visit(this);
    }

}
