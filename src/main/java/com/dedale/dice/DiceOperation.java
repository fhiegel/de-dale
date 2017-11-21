package com.dedale.dice;

import com.dedale.core.expression.AbstractArithmeticOperation;
import com.dedale.core.expression.ArithmeticExpression;
import com.dedale.core.expression.CommandCombiner;
import com.dedale.core.expression.Expression;
import com.dedale.core.expression.IntegerFunction;

public class DiceOperation extends AbstractArithmeticOperation {

    public DiceOperation() {
        this(new DiceSum());
    }

    public DiceOperation(DiceSum sum) {
        super(sum::rollKindOf);
    }

    private DiceOperation(IntegerFunction operation, Expression left, Expression right) {
        super(operation, left, right);
    }

    private DiceOperation(CommandCombiner command, Expression left, Expression right) {
        super(command, left, right);
    }

    @Override
    protected ArithmeticExpression copy(Expression left, Expression right) {
        return new DiceOperation(this.command, left, right);
    }

}
