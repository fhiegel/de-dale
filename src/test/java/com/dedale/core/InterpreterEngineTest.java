package com.dedale.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.dedale.core.expression.Expression;

public class InterpreterEngineTest {

    private InterpreterEngine interpreter = new InterpreterEngine(InterpreterEngine.expressionParser());

    @Test
    public void should_return_given_text() throws Exception {
        String input = "Some text";
        Expression result1 = interpreter.interpret(input);

        String result = result1.print();

        assertThat(result).isEqualTo("Some text");
    }

    @Test
    public void should_return_given_bold_text() throws Exception {
        String input = "Some text --bold";
        Expression result1 = interpreter.interpret(input);

        String result = result1.print();

        assertThat(result).isEqualTo("*Some text*");
    }

    @Test
    public void should_show_some_stuff() throws Exception {
        String input = "show";
        Expression result1 = interpreter.interpret(input);

        String result = result1.print();

        assertThat(result).isEqualTo("...");
    }

    @Test
    public void should_call_function_with_args() throws Exception {
        String input = "recursivesubcmd show --bold";
        Expression result1 = interpreter.interpret(input);

        String result = result1.print();

        assertThat(result).isEqualTo("*...*");
    }
    
    @Test
    public void should_call_other_interpreter() throws Exception {
        String input = "roll 1+2*3+4-5";
        Expression result1 = interpreter.interpret(input);
        
        String result = result1.print();
        
        assertThat(result).isEqualTo("6");
    }

    @Test
    public void should_do_when() throws Exception {
        // Given
        Pattern pattern = Pattern.compile("^subcmd\\s(.*)$|(\\S)+");
        Matcher matcher = pattern.matcher("subcmd some - text");
        matcher.find();
        // When
        // Then
        assertThat(matcher.group(1).toString()).isEqualTo("some - text");
    }
}
