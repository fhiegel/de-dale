package com.dedale.core.parse;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.dedale.core.parse.RawArguments;

public class RawArgumentsTest {

    public static final String pattern = "(\\d+)|([\\+])|([dD])";

    @Test
    public void should_parse_single_digit() throws Exception {
        String[] arguments = RawArguments.splitArguments("1", pattern);
        assertThat(arguments).containsExactly("1");
    }

    @Test
    public void should_parse_digits_with_operation_in_three() throws Exception {
        String[] arguments = RawArguments.splitArguments("1+2", pattern);
        assertThat(arguments).containsExactly("1", "+", "2");
    }
    
    @Test
    public void should_parse_digits_with_dice_operation_in_three() throws Exception {
        String[] arguments = RawArguments.splitArguments("1d2", pattern);
        assertThat(arguments).containsExactly("1", "d", "2");
    }

}
