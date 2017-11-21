package com.dedale.dice;

import java.util.function.Supplier;

public class DiceSum {

    private DiceProvider dicePool;

    public DiceSum() {
        this(DiceProvider.DEFAULT_DICES);
    }

    public DiceSum(DiceProvider dicePool) {
        this.dicePool = dicePool;
    }

    public Integer rollKindOf(int rollCount, int faces) {
        Supplier<Integer> roller = () -> dicePool.getDice(faces).roll();
        
        int sum = 0;
        for (int i = 1; i <= rollCount; i++) {
            sum += roller.get();
        }
        return sum;
    }
}