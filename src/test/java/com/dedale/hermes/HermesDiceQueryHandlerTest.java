package com.dedale.hermes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.dedale.calculator.DiceRollsOperations;
import com.dedale.calculator.StringCalculator;
import com.dedale.calculator.StringToIntegerOperation;
import com.dedale.dice.DiceSum;

public class HermesDiceQueryHandlerTest {

    private DiceSum diceRule = mock(DiceSum.class);

    private HermesDiceQueryHandler handler = new HermesDiceQueryHandler(
            StringCalculator.arithmeticOperations().addOperation(diceOperation()));

    private StringToIntegerOperation diceOperation() {
        return DiceRollsOperations.dicesOperation(diceRule);
    }

    @Test
    public void should_default_command_sum_dices() throws Exception {
        // Given
        given(diceRule.rollKindOf(1, 20)).willReturn(5);
        HermesCommand hermesCommand = new HermesCommand("1d20+5", "Dummy the dwarf");

        // When
        HermesDiceResult response = handler.handle(hermesCommand);

        // Then
        assertThat(response.getText()).isEqualTo("1d20+5= *10*");
    }

}
