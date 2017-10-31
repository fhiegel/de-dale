package com.dedale.core.expression;

public interface Expression {

    Expression NEUTRAL = new Neutral();

    Expression then(Expression after);

    Expression evaluate();

    /**
     * On devrait utiliser un Visitor à la place.
     */
    @Deprecated
    String print();

    default Expression accept(ExpressionVisitor visitor) {
        return visitor.visit(this);
    }

}
