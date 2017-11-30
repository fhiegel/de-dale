package com.dedale.core.engine.expression;

public abstract class AbstractExpression implements Expression {

    public final Expression then(Expression after) {
        SyntaxTreeVisitor syntaxTree = defaultDispatcher();
        configure(syntaxTree);
        SyntaxTreeVisitor visit = syntaxTree.visit(after);
        return visit.next();
    }
    
    protected SyntaxTreeVisitor configure(SyntaxTreeVisitor syntaxTree) {
        return syntaxTree;
    }

    private SyntaxTreeVisitor defaultDispatcher() {
        return new SyntaxTreeVisitor(this)
                .when(Neutral.class, neutral -> this)
                .otherwise(THEN.left(this));
    }

    @Override
    public String toString() {
        ExpressionPrinter printer = new ExpressionPrinter();
        accept(printer);
        return getClass().getSimpleName() + "{" + printer.print() + "}";
    }

}
