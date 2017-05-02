package com.dedale.dice;

public class DiceSum {
    
    private DiceThrower diceThrower = new DiceThrower();
    
    public Integer rollKindOf(int rollCount, int diceType) {
        int sum = 0;
        for (int i = 1; i <= rollCount; i++) {
            sum += diceThrower.rollDice(diceType);
        }
        return sum;
    }
}