package com.dedale.calculator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class StringCalculatorInputValidatorTest {

    static final StringCalculator ADD_CALCULATOR = StringCalculator.empty().addOperation(ArithmeticOperations.ADD);

    @Test
    public void should_validate_number() throws Exception {
        // Given
        StringCalculatorInputValidator validator = new StringCalculatorInputValidator(ADD_CALCULATOR);

        // When
        boolean valid = validator.validate("123");

        // Then
        assertThat(valid).isTrue();
    }

    @Test
    public void should_invalidate_empty() throws Exception {
        // Given
        StringCalculatorInputValidator validator = new StringCalculatorInputValidator(ADD_CALCULATOR);

        // When
        boolean valid = validator.validate("");

        // Then
        assertThat(valid).isFalse();
    }

    @Test
    public void should_invalidate_inexistent_operation() throws Exception {
        // Given
        StringCalculatorInputValidator validator = new StringCalculatorInputValidator(ADD_CALCULATOR);

        // When
        boolean valid = validator.validate("1-1");

        // Then
        assertThat(valid).isFalse();
    }

    @Test
    public void should_validate_operation() throws Exception {
        // Given
        StringCalculatorInputValidator validator = new StringCalculatorInputValidator(ADD_CALCULATOR);

        // When
        boolean valid = validator.validate("1+1");

        // Then
        assertThat(valid).isTrue();
    }

    @Test
    public void should_invalidate_redundant_operation() throws Exception {
        // Given
        StringCalculatorInputValidator validator = new StringCalculatorInputValidator(ADD_CALCULATOR);

        // When
        boolean valid = validator.validate("1++1");

        // Then
        assertThat(valid).isFalse();
    }

    @Test
    public void should_validate_number_with_spaces() throws Exception {
        // Given
        StringCalculatorInputValidator validator = new StringCalculatorInputValidator(ADD_CALCULATOR);

        // When
        boolean valid = validator.validate("  123 ");

        // Then
        assertThat(valid).isTrue();
    }

}
