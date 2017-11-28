package com.dedale.core.calculator;

import static java.lang.Math.pow;

import com.dedale.core.engine.expression.Expression;
import com.dedale.core.engine.expression.SyntaxTreeVisitor;

public class PowerOperation extends AbstractArithmeticOperation {

    private static final IntegerFunction POW = (left, right) -> (int) pow(left, right);
    public static final PowerOperation EMPTY = new PowerOperation();

    private PowerOperation() {
        super(POW);
    }

    private PowerOperation(Expression left, Expression right) {
        super(POW, left, right);
    }

    @Override
    protected ArithmeticExpression copy(Expression left, Expression right) {
        return new PowerOperation(left, right);
    }

    @Override
    protected SyntaxTreeVisitor configure(SyntaxTreeVisitor syntaxTree) {
        return super.configure(syntaxTree)
                .when(PowerOperation.class, pow -> pow.assignLeft(this))
                .when(MultiplyOperation.class, multiply -> multiply.assignLeft(this));
    }

}
