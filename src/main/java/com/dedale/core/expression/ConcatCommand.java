package com.dedale.core.expression;

public class ConcatCommand extends AbstractCommand {

    public static final ConcatCommand CONCAT = new ConcatCommand();
    private static final String CONCAT_SEPARATOR = " ";

    private ConcatCommand() {
        super((l, r) -> new TextExpression(l.print() + CONCAT_SEPARATOR + r.print()));
    }

    private ConcatCommand(Expression left, Expression right) {
        super((l, r) -> new TextExpression(l.print() + CONCAT_SEPARATOR + r.print()), left, right);
    }

    @Override
    protected ExpressionVisitor configure(ExpressionVisitor dispatcher) {
        return dispatcher.when(BoldTextExpression.class, bold -> bold.wrapText(this)).when(Expression.class, this::assignRight);
    }

    static ConcatCommand concat(Expression left, Expression right) {
        return new ConcatCommand(left, right);
    }

    @Override
    public String print() {
        return printDelegate(left) + CONCAT_SEPARATOR + printDelegate(right);
    }

    @Override
    protected Expression copy(Expression left, Expression right) {
        return concat(left, right);
    }

}
