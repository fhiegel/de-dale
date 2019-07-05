package com.dedale.core.text;

import com.dedale.core.engine.ExecutionContext;
import com.dedale.core.engine.InterpreterEngine;
import com.dedale.core.engine.expression.ExpressionPrinter;
import com.dedale.core.engine.expression.TextExpression;
import com.dedale.core.engine.expression.Then;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TextingFeatures {

    private Texting module = new Texting();
    private InterpreterEngine engine = new InterpreterEngine(module);
    private ExecutionContext context = engine.defaultContext();

    @Test
    void text_instruction_is_a_text() throws Exception {
        // Given
        context = context.withInput("Text");

        // When
        TextExpression result = (TextExpression) engine.interpret(context);

        // Then
        assertThat(result.value()).isEqualTo("Text");
    }

    @Test
    void multiple_text_instructions_are_concat() throws Exception {
        // Given
        context = context.withInput("Some text");

        // When
        Then result = (Then) engine.interpret(context);

        // Then
        assertThat(print(result)).isEqualTo("Some text");
    }

    private String print(Then result) {
        ExpressionPrinter printer = new ExpressionPrinter();
        result.accept(printer);
        return printer.print();
    }

}
