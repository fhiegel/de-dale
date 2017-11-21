package com.dedale.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.dedale.core.expression.Expression;
import com.dedale.core.expression.ExpressionPrinter;
import com.dedale.core.expression.TextExpression;

public class InterpreterFeatures {

    private CoreModule module = new CoreModule(CoreModule.expressionParser());

    @Test
    public void should_return_given_text() throws Exception {
        String input = "Some text";
        Expression expression = interpret(input);

        String result = print(expression);
        assertThat(result).isEqualTo("Some text");
    }

    @Test
    public void should_return_given_bold_text() throws Exception {
        String input = "Some text --bold";
        Expression expression = interpret(input);

        String result = print(expression);

        assertThat(result).isEqualTo("*Some text*");
    }

    @Test
    public void should_show_some_stuff() throws Exception {
        String input = "show";
        Expression expression = interpret(input);

        String result = print(expression);

        assertThat(result).isEqualTo("...");
    }

    @Test
    public void should_call_function_with_args() throws Exception {
        String input = "recursivesubcmd show --bold";
        Expression expression = interpret(input);

        String result = print(expression);

        assertThat(result).isEqualTo("*...*");
    }

    @Test
    public void should_call_other_interpreter() throws Exception {
        String input = "roll 1+2*3+4-5";
        Expression expression = interpret(input);

        String result = print(expression);

        assertThat(result).isEqualTo("6");
    }

    @Test
    public void interpret_commands_when_other_contains_keywords() throws Exception {
        module = new CoreModule(CommandDefinitions
                .defineCommands()
                .withCommand("doSomethingElse", new TextExpression("Text when Do Something Else."))

                .andParse("do")
                .strictly()
                .with()
                .constant(new TextExpression("Text when Do."))
                .build());

        Expression expression = interpret("do");
        String result = print(expression);

        assertThat(result).isEqualTo("Text when Do.");

        expression = interpret("doSomethingElse");
        result = print(expression);

        assertThat(result).isEqualTo("Text when Do Something Else.");
    }

    //
    // Utilitaires
    //

    private String print(Expression expression) {
        ExpressionPrinter printer = new ExpressionPrinter();
        expression.accept(printer);
        return printer.print();
    }

    public Expression interpret(String input) {
        InterpreterEngine engine = new InterpreterEngine(module);
        ExecutionContext context = ExecutionContext.from(engine);
        return engine.interpret(context, input);
    }
}
