package com.dedale.calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.dedale.dice.DiceSum;

public class StringCalculatorTest {
    
    private StringCalculator calculator;
    
    @Before
    public void initializeDicecalcr() throws Exception {
        calculator = new StringCalculator();
    }
    
    @Test
    public void returns_one_when_string_one_given() throws Exception {
        // Given
        String sentence = "1";
        
        Integer result = calculator.calculate(sentence);
        
        // Then
        assertThat(result).isEqualTo(1);
    }
    
    @Test
    public void returns_two_when_string_two_given() throws Exception {
        // Given
        String sentence = "2";
        
        // When
        Integer result = calculator.calculate(sentence);
        
        // Then
        assertThat(result).isEqualTo(2);
    }
    
    @Test
    public void returns_two_when_string_oneplusone_given() throws Exception {
        // Given
        String sentence = "1+1";
        
        // When
        Integer result = calculator.calculate(sentence);
        
        // Then
        assertThat(result).isEqualTo(2);
    }
    
    @Test
    @Ignore("Should find what we expect")
    public void returns_two_when_string_oneplusone_given_foo() throws Exception {
        // Given
        String sentence = "1+" + Integer.MAX_VALUE;
        
        // When
        Integer result = calculator.calculate(sentence);
        
        // Then
        assertThat(result).isEqualTo(2);
    }
    
    @Test
    public void returns_three_when_string_oneplusoneplusone_given() throws Exception {
        // Given
        String sentence = "1+1+1";
        
        // When
        Integer result = calculator.calculate(sentence);
        
        // Then
        assertThat(result).isEqualTo(3);
    }
    
    @Test
    public void returns_zero_when_string_oneminussone_given() throws Exception {
        // Given
        String sentence = "1-1";
        
        // When
        Integer result = calculator.calculate(sentence);
        
        // Then
        assertThat(result).isEqualTo(0);
    }
    
    @Test
    public void returns_one_when_string_oneplusoneminussone_given() throws Exception {
        // Given
        String sentence = "1+1-1";
        
        // When
        Integer result = calculator.calculate(sentence);
        
        // Then
        assertThat(result).isEqualTo(1);
    }
    
    @Test
    public void returns_one_when_string_oneminusoneplussone_given() throws Exception {
        // Given
        String sentence = "1-1+1";
        
        // When
        Integer result = calculator.calculate(sentence);
        
        // Then
        assertThat(result).isEqualTo(1);
    }
    
    @Test
    public void returns_one_when_string_one_multiply_one_given() throws Exception {
        // Given
        String sentence = "1*1";
        
        // When
        Integer result = calculator.calculate(sentence);
        
        // Then
        assertThat(result).isEqualTo(1);
    }
    
    @Test
    public void returns_eight_when_string_two_multiply_two_multiply_two_given() throws Exception {
        // Given
        String sentence = "2*2*2";
        
        // When
        Integer result = calculator.calculate(sentence);
        
        // Then
        assertThat(result).isEqualTo(8);
    }
    
    @Test
    public void returns_six_when_string_two_multiply_two_plus_two_given() throws Exception {
        // Given
        String sentence = "2+2*2";
        
        // When
        Integer result = calculator.calculate(sentence);
        
        // Then
        assertThat(result).isEqualTo(6);
    }
    
    @Test
    public void returns_one_when_string_two_power_zero_given() throws Exception {
        // Given
        String sentence = "2^0";
        
        // When
        Integer result = calculator.calculate(sentence);
        
        // Then
        assertThat(result).isEqualTo(1);
    }
    
    @Test
    public void returns_one_when_string_two_power_one_given() throws Exception {
        // Given
        String sentence = "2^1";
        
        // When
        Integer result = calculator.calculate(sentence);
        
        // Then
        assertThat(result).isEqualTo(2);
    }
    
    @Test
    public void returns_64_when_string_two_power_six_given() throws Exception {
        // Given
        String sentence = "2^6";
        
        // When
        Integer result = calculator.calculate(sentence);
        
        // Then
        assertThat(result).isEqualTo(64);
    }
    
    @Test
    public void returns_64_when_string_two_power_three_power_two_given() throws Exception {
        // Given
        String sentence = "2^3^2";
        
        // When
        Integer result = calculator.calculate(sentence);
        
        // Then
        assertThat(result).isEqualTo(64);
    }
    
    @Test
    public void returns_32_when_string_two_power_three_multiply_two_power_two_given() throws Exception {
        // Given
        String sentence = "2^3*2^2";
        
        // When
        Integer result = calculator.calculate(sentence);
        
        // Then
        assertThat(result).isEqualTo(32);
    }
    
    @Test
    public void returns_3_for_throwing_1d4() throws Exception {
        // Given
        DiceSum adaptee = mock(DiceSum.class);
        when(adaptee.rollKindOf(anyInt(), anyInt())).thenReturn(3);
        
        StringToIntegerOperation dice = new StringToIntegerOperation("d", OperandReader.LEFT_TO_RIGHT, (l,r) -> SumDiceRollsOperation.sumDices(adaptee, l, r));
        calculator = new StringCalculator(Arrays.asList(dice));
        
        String sentence = "1d4";
        
        // When
        Integer result = calculator.calculate(sentence);
        
        // Then
        assertThat(result).isEqualTo(3);
    }
    
}
