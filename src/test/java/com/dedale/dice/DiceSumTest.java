package com.dedale.dice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiceSumTest {

    private static final int d6 = 6;

    @Mock
    private Dice dice;

    @Mock
    private DiceProvider diceThrower;

    @InjectMocks
    private DiceSum diceSum;

    @BeforeEach
    void beforeName() {
        when(diceThrower.getDice(d6)).thenReturn(dice);
    }

    @Test
    void should_sum_dices_return_zero_when_no_dice_thrown() {
        Mockito.reset(diceThrower);

        Integer result = diceSum.rollKindOf(0, d6);

        assertThat(result).isEqualTo(0);
        verifyZeroInteractions(diceThrower);
    }

    @Test
    void should_sum_dices_return_single_dice_value() {
        // Given
        diceWillReturn(1);

        // When
        Integer result = diceSum.rollKindOf(1, d6);

        // Then
        assertThat(result).isEqualTo(1);
    }

    @Test
    void should_sum_dices_return_sum_of_dice_results() {
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
