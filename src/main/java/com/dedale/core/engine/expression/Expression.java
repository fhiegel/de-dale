package com.dedale.core.engine.expression;

import com.dedale.core.engine.ExecutionContext;

public interface Expression {

    Expression NEUTRAL = new Neutral();

    Expression then(Expression after);

    Expression execute(ExecutionContext context);

    default <R> void accept(ExpressionVisitor<R> visitor) {
        visitor.visit(this);
    }

}
