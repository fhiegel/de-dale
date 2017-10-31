package com.dedale.dice;

import java.util.Random;

public class Dice {

    private int faces;

    public Dice(int faces) {
        this.faces = faces;
    }

    public int roll() {
        return new Random().ints(1, faces + 1).findAny().getAsInt();
    }

}
