package com.dedale.core.engine.expression;

import java.util.function.Function;

public class ConcatCommand extends AbstractCommand {

    public static final ConcatCommand CONCAT = new ConcatCommand();
    static final String CONCAT_SEPARATOR = " ";

    private ConcatCommand() {
        super(ConcatCommand::concat);
    }

    private ConcatCommand(Expression left, Expression right) {
        super(ConcatCommand::concat, left, right);
    }

    @Override
    protected ExpressionVisitor<Expression> configure(ExpressionVisitor<Expression> dispatcher) {
        return dispatcher.when(BoldTextExpression.class, bold -> bold.wrapText(this)).when(Expression.class, this::assignRight);
    }

    private static ConcatCommand concat(Expression left, Expression right) {
        return new ConcatCommand(left, right);
    }

    @Override
    protected Expression copy(Expression left, Expression right) {
        return concat(left, right);
    }

    public Function<Expression, Expression> left(Expression left) {
        return right -> concat(left, right);
    }

}
