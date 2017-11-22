package com.dedale.core.engine.expression;

import com.dedale.core.engine.ExecutionContext;

public abstract class AbstractCommand extends AbstractExpression {

    protected final CommandCombiner command;
    protected final Expression left;
    protected final Expression right;

    protected AbstractCommand(CommandCombiner command) {
        this(command, null, null);
    }

    protected AbstractCommand(CommandCombiner command, Expression left, Expression right) {
        this.command = command;
        this.left = left;
        this.right = right;
    }

    @Override
    public Expression execute(ExecutionContext context) {
        Expression evaluatedLeft = left.execute(context);
        Expression evaluatedRight = right.execute(context);
        return command.execute(evaluatedLeft, evaluatedRight);
    }

    public Expression assignLeft(Expression left) {
        Expression effectiveLeft = this.left == null ? left : this.left.then(left);
        return copy(effectiveLeft, right);
    }

    public Expression assignRightWhenNotFull(Expression right) {
        if (!isFullAtRight()) {
            return assignRight(right);
        }
        return ConcatCommand.CONCAT.left(this).apply(right);
    }

    boolean isFullAtRight() {
        return right != null && !(right instanceof AbstractCommand)
                || (right instanceof AbstractCommand && ((AbstractCommand) right).isFullAtRight());
    }

    public Expression assignRight(Expression right) {
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
