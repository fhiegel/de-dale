package com.dedale.core.expression;

public abstract class AbstractCommand extends AbstractExpression {

    protected final Command command;
    protected final Expression left;
    protected final Expression right;

    protected AbstractCommand(Command command) {
        this(command, null, null);
    }

    protected AbstractCommand(Command command, Expression left, Expression right) {
        this.command = command;
        this.left = left;
        this.right = right;
    }

    @Override
    public Expression evaluate() {
        Expression evaluatedLeft = left.evaluate();
        Expression evaluatedRight = right.evaluate();
        return command.execute(evaluatedLeft, evaluatedRight);
    }

    Expression assignLeft(Expression left) {
        Expression effectiveLeft = this.left == null ? left : this.left.then(left);
        return copy(effectiveLeft, right);
    }

    Expression assignRightWhenNotFull(Expression right) {
        if (!isFullAtRight()) {
            return assignRight(right);
        }
        return ConcatCommand.concat(this, right);
    }

    boolean isFullAtRight() {
        return right != null && !(right instanceof AbstractCommand)
                || (right instanceof AbstractCommand && ((AbstractCommand) right).isFullAtRight());
    }

    Expression assignRight(Expression right) {
        Expression effectiveRight = this.right == null ? right : this.right.then(right);
        return copy(left, effectiveRight);
    }

    protected abstract Expression copy(Expression left, Expression right);
    
    @Override
    public <R> void accept(ExpressionVisitor<R> visitor) {
        if (left != null) left.accept(visitor);
        super.accept(visitor);
        if (right != null) right.accept(visitor);
    }

}
