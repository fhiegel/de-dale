package com.dedale.dice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class DiceSumTest {
    
    private static final int d6 = 6;
    
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();
    
    @Mock
    private DiceThrower diceThrower;
    
    @InjectMocks
    private DiceSum diceSum;
    
    @Test
    public void should_sum_dices_return_zero_when_no_dice_thrown() throws Exception {
        Integer result = diceSum.rollKindOf(0, d6);
        assertThat(result).isEqualTo(0);
    }
    
    @Test
    public void should_sum_dices_return_single_dice_value() throws Exception {
        // Given
        diceWillReturn(d6, 1);
        
        // When
        Integer result = diceSum.rollKindOf(1, d6);
        
        // Then
        assertThat(result).isEqualTo(1);
    }
    
    @Test
    public void should_sum_dices_return_sum_of_dice_results() throws Exception {
        // Given
        diceWillReturn(d6, 3, 4, 5);
        
        // When
        Integer result = diceSum.rollKindOf(3, d6);
        
        // Then
        assertThat(result).isEqualTo(3 + 4 + 5);
    }
    
    private void diceWillReturn(int diceType, Integer value, Integer... values) {
        when(diceThrower.rollDice(eq(diceType))).thenReturn(value, values);
    }
    
}
