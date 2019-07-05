package com.dedale.dice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class DiceTest {

    private static final int d4 = 4;

    private Dice dice = new Dice(d4);

    @Test
    void dice_result_is_greater_or_equals_than_one() {
        for (int i = 0; i < 1000; i++) {
            int roll = dice.roll();
            assertThat(roll).isGreaterThanOrEqualTo(1);
        }
    }

    @Test
    void dice_result_is_lower_or_equals_than_dice_value() {
        for (int i = 0; i < 1000; i++) {
            int roll = dice.roll();
            assertThat(roll).isLessThanOrEqualTo(d4);
        }
    }

}
