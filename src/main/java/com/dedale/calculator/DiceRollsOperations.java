package com.dedale.calculator;

import java.util.function.Function;

import com.dedale.dice.DiceSum;

public class DiceRollsOperations {

    public static final StringToIntegerOperation SUM_DICES = dicesOperation(sumDiceRule());

    public static StringToIntegerOperation dicesOperation(DiceSum diceRule) {
        return new OperationBuilder<>(Function.identity()).withSymbols("d", "D").fromLeftToRight()
                .withOperation(applyDiceRule(diceRule)).endOperation();
    }

    private static IntegerOperation applyDiceRule(DiceSum diceRule) {
        return (left, right) -> diceRule.rollKindOf(left, right);
    }

    private static DiceSum sumDiceRule() {
        return new DiceSum();
    }

}
