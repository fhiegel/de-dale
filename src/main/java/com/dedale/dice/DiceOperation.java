package com.dedale.dice;

import com.dedale.core.calculator.AbstractArithmeticOperation;
import com.dedale.core.calculator.IntegerFunction;
import com.dedale.core.engine.expression.CommandCombiner;
import com.dedale.core.engine.expression.Expression;

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
    protected DiceOperation copy(Expression left, Expression right) {
        return new DiceOperation(this.command, left, right);
    }

}
