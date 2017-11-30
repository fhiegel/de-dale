package com.dedale.core.engine.expression;

import com.dedale.core.engine.ExecutionContext;

public interface Expression {

    Neutral NEUTRAL = Neutral.NEUTRAL;
    Then THEN = Then.THEN;

    Expression then(Expression after);

    Expression execute(ExecutionContext context);

    default <R, V extends ExpressionVisitor<R>> V accept(V visitor) {
        return visitor.visit(this);
    }

}
