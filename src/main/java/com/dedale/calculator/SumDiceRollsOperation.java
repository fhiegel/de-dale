package com.dedale.calculator;

import com.dedale.dice.DiceSum;

class SumDiceRollsOperation implements IntegerOperation {
    
    private DiceSum sumDices;
    
    private SumDiceRollsOperation(DiceSum sumDices) {
        this.sumDices = sumDices;
    }
    
    @Override
    public Integer apply(Integer left, Integer right) {
        return sumDices.rollKindOf(left, right);
    }
    
    static Integer sumDices(DiceSum sumDices, Integer rollCount, Integer diceType) {
        return new SumDiceRollsOperation(sumDices).apply(rollCount, diceType);
    }
    
    public static Integer sumDices(Integer diceCount, Integer diceType) {
        return sumDices(new DiceSum(), diceCount, diceType);
    }
    
}
