package com.dedale.dice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class DiceThrowerTest {
    
    private DiceThrower thrower = new DiceThrower();
    
    @Test
    public void should_dice_result_is_positive() throws Exception {
        int _1d4 = 4;
        for (int i = 0; i < 1000; i++) {
            int roll = thrower.rollDice(_1d4);
            assertThat(roll).isPositive();
        }
    }
    
    @Test
    public void should_dice_result_is_lower_or_equals_than_dice_high_score() throws Exception {
        int _1d4 = 4;
        for (int i = 0; i < 1000; i++) {
            int roll = thrower.rollDice(_1d4);
            assertThat(roll).isLessThanOrEqualTo(_1d4);
        }
    }
    
}
