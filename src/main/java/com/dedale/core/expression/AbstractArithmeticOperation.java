package com.dedale.core.expression;

public class AbstractArithmeticOperation extends AbstractCommand implements ArithmeticExpression {

    protected AbstractArithmeticOperation(IntegerFunction operation) {
        this(operation, null, null);
    }

    protected AbstractArithmeticOperation(IntegerFunction operation, Expression left, Expression right) {
        this(toCommand(operation), left, right);
    }

    protected AbstractArithmeticOperation(Command command, Expression left, Expression right) {
        super(command, left, right);
    }

    private static Command toCommand(IntegerFunction operation) {
        return (l, r) -> new IntegerExpression(operation.apply(((IntegerExpression) l).value(), ((IntegerExpression) r).value()));
    }

    @Override
    protected ExpressionVisitor<Expression> configure(ExpressionVisitor<Expression> visitor) {
        return visitor
                .when(AddOperation.class, add -> add.assignLeft(this))
                .when(MinusOperation.class, minus -> minus.assignLeft(this))

                .when(PowerOperation.class, pow -> this.assignRight(pow))
                .when(MultiplyOperation.class, multiply -> this.assignRight(multiply))

                .when(ArithmeticExpression.class, arithmetic -> this.assignRightWhenNotFull(arithmetic))
                .when(Expression.class, any -> ConcatCommand.concat(this, any));
    }

    @Override
    protected Expression copy(Expression left, Expression right) {
        return new AbstractArithmeticOperation(command, left, right);
    }

}
