package com.dedale.dice;

@FunctionalInterface
public interface DiceProvider {

    DiceProvider DEFAULT_DICES = faces -> new Dice(faces);

    Dice getDice(int faces);
}
