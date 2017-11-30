package com.dedale.core.calculator;

import com.dedale.core.engine.expression.Expression;
import com.dedale.core.engine.expression.SyntaxTreeVisitor;
import com.dedale.core.engine.expression.ValuedExpression;

public class IntegerExpression extends ValuedExpression<Integer> implements ArithmeticExpression {

    public IntegerExpression(Integer value) {
        super(value);
    }

    @Override
    protected SyntaxTreeVisitor configure(SyntaxTreeVisitor syntaxTree) {
        return syntaxTree
                .when(AbstractArithmeticOperation.class, operation -> operation.assignLeft(this))
                .otherwise(Expression.THEN.left(this));
    }

}
