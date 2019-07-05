package com.dedale.core.engine.expression;

import com.dedale.core.engine.ExecutionContext;
import org.junit.jupiter.api.Test;

import static com.dedale.core.engine.expression.Expression.NEUTRAL;
import static com.dedale.core.engine.expression.ExpressionSamples.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class ExpressionFeatures {

    private ExecutionContext context = mock(ExecutionContext.class);

    @Test
    void evaluate_neutral_will_return_initial_expression() {
        Expression expression = anExpression.then(Expression.NEUTRAL);
        assertThat(expression).isEqualTo(anExpression);
    }

    @Test
    void neutral_evaluate_expression_will_return_second_expression() {
        Expression expression = NEUTRAL.then(anExpression);
        assertThat(expression).isEqualTo(anExpression);
    }

    @Test
    void evaluate_two_text_expressions_will_return_expression_concatenation() {
        // Given
        TextExpression first = new TextExpression("one");
        TextExpression second = new TextExpression("sentence");

        // When
        Expression expression = first.then(second);
        String print = print(expression);

        // Then
        assertThat(print).isEqualTo("one sentence");
    }

    @Test
    void print_two_integer_expressions_an_one_operation_will_return_readable_result() {
        // When
        Expression result = one.then(add).then(two);

        // Then
        assertThat(print(result)).isEqualTo("1+2");
    }

    @Test
    void evaluate_two_integer_expressions_an_one_operation_will_return_readable_result() {
        // When
        Expression result = one.then(add).then(two).execute(context);

        // Then
        assertThat(print(result)).isEqualTo("3");
    }

    @Test
    void evaluate_two_integer_expressions_will_return_concat_result() {
        // When
        Expression result = one.then(two).execute(context);

        // Then
        assertThat(print(result)).isEqualTo("1 2");
    }

    @Test
    void evaluate_two_integer_expressions_will_return_concat_statement() {
        // When
        Expression then = one.then(two);

        // Then
        assertThat(then).isInstanceOf(Then.class);
    }

    @Test
    void prints_composite_sum() {

        // When
        Expression result = one.then(add).then(two).then(add).then(four);

        // Then
        assertThat(print(result)).isEqualTo("1+2+4");
    }

    @Test
    void one_plus_two_plus_four_equals_seven() {

        // When
        Expression notEvaluated = one.then(add).then(two).then(add).then(four);
        Expression result = notEvaluated.execute(context);

        // Then
        assertThat(print(notEvaluated)).isEqualTo("1+2+4");
        assertThat(print(result)).isEqualTo("7");
    }

    @Test
    void one_plus_two_multiply_three_equals_seven() {
        // When
        Expression notEvaluated = one.then(add).then(two).then(multiply).then(three);
        Expression result = notEvaluated.execute(context);

        // Then
        assertThat(print(notEvaluated)).isEqualTo("1+2*3");
        assertThat(print(result)).isEqualTo("7");
    }

    @Test
    void one_plus_two_multiply_three_multiply_four_equals_twenty_five() {
        // When
        Expression notEvaluated = one.then(add).then(two).then(multiply).then(three).then(multiply).then(four);
        Expression result = notEvaluated.execute(context);

        // Then
        assertThat(print(notEvaluated)).isEqualTo("1+2*3*4");
        assertThat(print(result)).isEqualTo("25");
    }

    @Test
    void one_plus_two_multiply_three_plus_four_equals_eleven() {
        // When
        Expression notEvaluated = one.then(add).then(two).then(multiply).then(three).then(add).then(four);
        Expression result = notEvaluated.execute(context);

        // Then
        assertThat(print(notEvaluated)).isEqualTo("1+2*3+4");
        assertThat(print(result)).isEqualTo("11");
    }

    @Test
    void seven_minus_two_multiply_three_equals_one() {
        // When
        Expression notEvaluated = seven.then(minus).then(two).then(multiply).then(three);
        Expression result = notEvaluated.execute(context);

        // Then
        assertThat(print(notEvaluated)).isEqualTo("7-2*3");
        assertThat(print(result)).isEqualTo("1");
    }

    @Test
    void two_power_three_multiply_two_power_two_equals_thirty_two() {
        // When
        Expression notEvaluated = two.then(power).then(three).then(multiply).then(two).then(power).then(two);
        Expression result = notEvaluated.execute(context);

        // Then
        assertThat(print(notEvaluated)).isEqualTo("2^3*2^2");
        assertThat(print(result)).isEqualTo("32");
    }

    private String print(Expression expression) {
        ExpressionPrinter printer = new ExpressionPrinter();
        expression.accept(printer);
        return printer.print();
    }

}
