package com.dedale.dice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
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
    private Dice dice;
    
    @Mock
    private DiceProvider diceThrower;

    @InjectMocks
    private DiceSum diceSum;
    
    @Before
    public void beforeName() throws Exception {
        when(diceThrower.getDice(d6)).thenReturn(dice);
    }

    @Test
    public void should_sum_dices_return_zero_when_no_dice_thrown() throws Exception {
        Integer result = diceSum.rollKindOf(0, d6);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void should_sum_dices_return_single_dice_value() throws Exception {
        // Given
        diceWillReturn(1);

        // When
        Integer result = diceSum.rollKindOf(1, d6);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void should_sum_dices_return_sum_of_dice_results() throws Exception {
        // Given
        diceWillReturn(3, 4, 5);

        // When
        Integer result = diceSum.rollKindOf(3, d6);

        // Then
        assertThat(result).isEqualTo(3 + 4 + 5);
    }

    private void diceWillReturn(Integer value, Integer... values) {
        when(dice.roll()).thenReturn(value, values);
    }

}
