package com.dedale.core.expression;

public abstract class AbstractExpression implements Expression {

    public final Expression then(Expression after) {
        ExpressionVisitor<Expression> dispatcher = defaultDispatcher();
        configure(dispatcher);
        return dispatcher.visit(after);
    }
    
    protected ExpressionVisitor<Expression> configure(ExpressionVisitor<Expression> dispatcher) {
        return dispatcher;
    }

    private ExpressionVisitor<Expression> defaultDispatcher() {
        return new OriginStatementVisitor(this)
                .when(Neutral.class, neutral -> this);
    }

    @Override
    public String toString() {
        ExpressionPrinter printer = new ExpressionPrinter();
        accept(printer);
        return getClass().getSimpleName() + "{" + printer.print() + "}";
    }

}
