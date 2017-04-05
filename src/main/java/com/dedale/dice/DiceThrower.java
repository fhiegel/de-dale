package com.dedale.dice;

import java.util.Random;

public class DiceThrower {
    
    public int rollDice(int dice) {
        return new Random().ints(1, dice + 1).findAny().getAsInt();
    }
    
}
