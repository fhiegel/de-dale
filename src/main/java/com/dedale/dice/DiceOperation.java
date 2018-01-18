package com.dedale.dice;

import com.dedale.core.calculator.AbstractArithmeticOperation;
import com.dedale.core.calculator.IntegerExpression;
import com.dedale.core.calculator.IntegerFunction;
import com.dedale.core.engine.expression.CommandCombiner;
import com.dedale.core.engine.expression.Expression;

public class DiceOperation extends AbstractArithmeticOperation {

    public DiceOperation() {
        this(new DiceSum());
    }

    public DiceOperation(DiceSum sum) {
        this(sum::rollKindOf, null, null);
    }

    private DiceOperation(IntegerFunction operation, Expression left, Expression right) {
        this(toCommand(operation), left, right);
    }

    private DiceOperation(CommandCombiner command, Expression left, Expression right) {
        super(command, left, right);
    }

    private static CommandCombiner toCommand(IntegerFunction operation) {
        return (l, r) -> new DiceResult(((IntegerExpression) l).value(), ((IntegerExpression) r).value(),
                operation.apply(((IntegerExpression) l).value(), ((IntegerExpression) r).value()));
    }

    @Override
    protected DiceOperation copy(Expression left, Expression right) {
        return new DiceOperation(this.command, left, right);
    }

}
