package com.dedale.core.calculator;

import com.dedale.core.engine.expression.ConcatCommand;
import com.dedale.core.engine.expression.Expression;
import com.dedale.core.engine.expression.ExpressionVisitor;
import com.dedale.core.engine.expression.ValuedExpression;

public class IntegerExpression extends ValuedExpression<Integer> implements ArithmeticExpression {

    public IntegerExpression(Integer value) {
        super(value);
    }

    @Override
    protected ExpressionVisitor<Expression> configure(ExpressionVisitor<Expression> dispatcher) {
        return dispatcher
                .when(AbstractArithmeticOperation.class, operation -> operation.assignLeft(this))
                .when(Expression.class, ConcatCommand.CONCAT.left(this));
    }

}
