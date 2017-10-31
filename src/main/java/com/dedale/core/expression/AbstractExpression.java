package com.dedale.core.expression;

public abstract class AbstractExpression implements Expression {

    public final Expression then(Expression after) {
        ExpressionVisitor dispatcher = defaultDispatcher();
        dispatcher = configure(dispatcher);
        return after.accept(dispatcher);
    }
    
    protected ExpressionVisitor configure(ExpressionVisitor dispatcher) {
        return dispatcher;
    }

    private ExpressionVisitor defaultDispatcher() {
        return new OriginStatementVisitor(this)
                .when(Neutral.class, neutral -> this);
    }

    protected final String printDelegate(Expression delegate) {
        return delegate != null ? delegate.print() : "null";
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + print() + "}";
    }

}
