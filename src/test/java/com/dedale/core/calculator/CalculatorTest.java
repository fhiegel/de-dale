package com.dedale.core.calculator;

import com.dedale.core.engine.CommandDefinitions;
import com.dedale.core.engine.CommandModule;
import com.dedale.core.engine.ExecutionContext;
import com.dedale.core.engine.InterpreterEngine;
import com.dedale.core.engine.expression.Expression;
import com.dedale.core.engine.expression.ExpressionPrinter;
import com.dedale.core.text.Texting;
import com.dedale.dice.Dice;
import com.dedale.dice.DiceOperation;
import com.dedale.dice.DiceSum;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalculatorTest {

    private CommandModule module = new Calculator();

    @Test
    void should_return_given_number() {
        String input = "1";

        String result = calculate(input);

        assertThat(result).isEqualTo("1");
    }

    @Test
    void one_plus_two_equals_three() {
        String input = "1+2";

        String result = calculate(input);

        assertThat(result).isEqualTo("3");
    }

    @Test
    void one_plus_two_equals_three_when_spaced() {
        String input = "1 + 2";

        String result = calculate(input);

        assertThat(result).isEqualTo("3");
    }

    @Test
    void one_plus_two_plus_three_equals_six() {
        String input = "1+2+4";

        String result = calculate(input);

        assertThat(result).isEqualTo("7");
    }

    @Test
    void one_and_one_return_one_and_one() {
        String input = "1 1";

        String result = calculate(input);

        assertThat(result).isEqualTo("1 1");
    }

    @Test
    void one_plus_two_and_one_plus_two_return_three_and_three() {
        String input = "1+2 1+2";

        String result = calculate(input);

        assertThat(result).isEqualTo("3 3");
    }

    @Test
    void three_minus_two_equals_one() {
        String input = "3-2";

        String result = calculate(input);

        assertThat(result).isEqualTo("1");
    }

    @Test
    void nine_minus_five_minus_three_equals_one() {
        String input = "9-5-3";

        String result = calculate(input);

        assertThat(result).isEqualTo("1");
    }

    @Test
    void two_multiply_three_equals_six() {
        String input = "2*3";

        String result = calculate(input);

        assertThat(result).isEqualTo("6");
    }

    @Test
    void two_multiply_three_multiply_four_equals_twenty_four() {
        String input = "2*3*4";

        String result = calculate(input);

        assertThat(result).isEqualTo("24");
    }

    @Test
    void two_multiply_three_plus_four_equals_ten() {
        String input = "2*3+4";

        String result = calculate(input);

        assertThat(result).isEqualTo("10");
    }

    @Test
    void two_plus_three_multiply_four_minus_five_equals_nine() {
        String input = "2+3*4-5";

        String result = calculate(input);

        assertThat(result).isEqualTo("9");
    }

    @Test
    void returns_one_when_string_two_power_zero_given() {
        String sentence = "2^0";

        String result = calculate(sentence);

        assertThat(result).isEqualTo("1");
    }

    @Test
    void returns_one_when_string_two_power_one_given() {
        String sentence = "2^1";

        String result = calculate(sentence);

        assertThat(result).isEqualTo("2");
    }

    @Test
    void returns_64_when_string_two_power_six_given() {
        String sentence = "2^6";

        String result = calculate(sentence);

        assertThat(result).isEqualTo("64");
    }

    @Test
    void returns_64_when_string_two_power_three_power_two_given() {
        String sentence = "2^3^2";

        String result = calculate(sentence);

        assertThat(result).isEqualTo("64");
    }

    @Test
    void returns_32_when_string_two_power_three_multiply_two_power_two_given() {
        String sentence = "2^3*2^2";

        String result = calculate(sentence);

        assertThat(result).isEqualTo("32");
    }

    @Test
    void returns_3_for_throwing_1d4() {
        // Given
        Dice dice = mock(Dice.class);
        when(dice.roll()).thenReturn(3);

        CommandDefinitions statements = CommandDefinitions
                .defineCommands()
                .andParse("([dD])(?!\\a)").with().constant(new DiceOperation(new DiceSum(faces -> dice)))
                .build();
        module = new Calculator(statements);

        String sentence = "1d4";

        // When
        String result = calculate(sentence);

        // Then
        assertThat(result).isEqualTo("3");
    }

    @Test
    void returns_labelled_dice_result() throws Exception {
        // Given
        Dice dice = mock(Dice.class);
        when(dice.roll()).thenReturn(5, 3);

        CommandDefinitions statements = CommandDefinitions
                .defineCommands()
                .andParse("([dD])(?=\\d)").with().constant(new DiceOperation(new DiceSum(faces -> dice))).build()
                ;
        module = new Texting(new Calculator(statements));

        String sentence = "attaque: 1d20+5 degats: 1d4";

        // When
        String result = calculate(sentence);

        // Then
        assertThat(result).isEqualTo("attaque: 10 degats: 3");
    }

    //
    // Utilitaires
    //

    private String calculate(String input) {
        InterpreterEngine engine = new InterpreterEngine(module);
        ExecutionContext context = engine.defaultContext().withInput(input);

        Expression expression = engine.interpret(context);

        ExpressionPrinter printer = new ExpressionPrinter();
        expression.accept(printer);
        return printer.print();
    }

}