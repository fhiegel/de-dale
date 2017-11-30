package com.dedale.core.engine.expression;

import java.util.Objects;
import java.util.function.Function;

import com.dedale.core.engine.ExecutionContext;

public class Then implements Expression {

    static final Then THEN = new Then();

    private final Expression left;
    private final Expression right;

    private Then() {
        this(NEUTRAL, NEUTRAL);
    }

    private Then(Expression left, Expression right) {
        this.left = Objects.requireNonNull(left);
        this.right = Objects.requireNonNull(right);
    }

    @Override
    public Expression execute(ExecutionContext context) {
        Expression evaluatedLeft = left.execute(context);
        Expression evaluatedRight = right.execute(context);
        return then(evaluatedLeft, evaluatedRight);
    }

    @Override
    public <R, V extends ExpressionVisitor<R>> V accept(V visitor) {
        V actual = visitor;
        actual = left != null ? left.accept(actual) : actual;
        actual = Expression.super.accept(visitor);
        actual = right != null ? right.accept(actual) : actual;
        return actual;
    }

    private static Then then(Expression left, Expression right) {
        return new Then(left, right);
    }

    public Function<Expression, Expression> left(Expression left) {
        return right -> then(left, right);
    }

    @Override
    public Expression then(Expression after) {
        Expression effectiveRight = this.right == null ? after : this.right.then(after);
        return then(left, effectiveRight);
    }
}
