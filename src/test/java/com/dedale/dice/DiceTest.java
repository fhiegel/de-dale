package com.dedale.dice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class DiceTest {
    
    private static final int d4 = 4;
    
    private Dice dice = new Dice(d4);
    
    @Test
    public void dice_result_is_greater_or_equals_than_one() throws Exception {
        for (int i = 0; i < 1000; i++) {
            int roll = dice.roll();
            assertThat(roll).isGreaterThanOrEqualTo(1);
        }
    }
    
    @Test
    public void dice_result_is_lower_or_equals_than_dice_value() throws Exception {
        for (int i = 0; i < 1000; i++) {
            int roll = dice.roll();
            assertThat(roll).isLessThanOrEqualTo(d4);
        }
    }
    
}
