package com.dedale.dice;

import com.dedale.core.calculator.IntegerExpression;

public class DiceResult extends IntegerExpression {

    Integer rolls;
    Integer faces;

    public DiceResult(Integer rolls, Integer faces, Integer value) {
        super(value);
        this.rolls = rolls;
        this.faces = faces;
    }
    
}
