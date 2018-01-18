package com.dedale.core.calculator;

import com.dedale.core.engine.expression.AbstractCommand;
import com.dedale.core.engine.expression.CommandCombiner;
import com.dedale.core.engine.expression.Expression;
import com.dedale.core.engine.expression.SyntaxTreeVisitor;

public class AbstractArithmeticOperation extends AbstractCommand implements ArithmeticExpression {

    protected AbstractArithmeticOperation(IntegerFunction operation) {
        this(operation, null, null);
    }

    protected AbstractArithmeticOperation(IntegerFunction operation, Expression left, Expression right) {
        this(toCommand(operation), left, right);
    }

    protected AbstractArithmeticOperation(CommandCombiner command, Expression left, Expression right) {
        super(command, left, right);
    }

    private static CommandCombiner toCommand(IntegerFunction operation) {
        return (l, r) -> new IntegerExpression(operation.apply(((IntegerExpression) l).value(), ((IntegerExpression) r).value()));
    }

    @Override
    protected SyntaxTreeVisitor configure(SyntaxTreeVisitor visitor) {
        return visitor
                .when(AddOperation.class, add -> add.assignLeft(this))
                .when(MinusOperation.class, minus -> minus.assignLeft(this))

                .when(PowerOperation.class, pow -> this.assignRight(pow))
                .when(MultiplyOperation.class, multiply -> this.assignRight(multiply))

                .when(ArithmeticExpression.class, arithmetic -> this.assignRightWhenNotFull(arithmetic))
                .otherwise(Expression.THEN.left(this));
    }

    @Override
    protected Expression copy(Expression left, Expression right) {
        return new AbstractArithmeticOperation(command, left, right);
    }

}
