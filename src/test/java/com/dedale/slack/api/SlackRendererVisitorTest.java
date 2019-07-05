package com.dedale.slack.api;

import com.dedale.core.engine.CommandModule;
import com.dedale.core.engine.ExecutionContext;
import com.dedale.core.engine.InterpreterEngine;
import com.dedale.core.engine.expression.Expression;
import com.dedale.core.engine.expression.ExpressionSamples;
import com.dedale.dice.DiceResult;
import com.dedale.slack.message.SlackMessage;
import org.junit.jupiter.api.Test;

import static com.dedale.core.engine.expression.ExpressionSamples.text;
import static com.dedale.markdown.MarkdownTags.BOLD;
import static com.dedale.slack.message.SlackMessageBuilder.beginMessage;
import static org.assertj.core.api.Assertions.assertThat;

class SlackRendererVisitorTest {

    private static final String DEFAULT_USER_NAME = "an user name";

    private ExecutionContext context = new InterpreterEngine(CommandModule.EMPTY).defaultContext().withUser(DEFAULT_USER_NAME);

    @Test
    void an_expression_is_printed() throws Exception {
        // Given
        Expression expression = ExpressionSamples.anExpression;

        // When
        SlackMessage slack = slackMessage(expression);

        // Then
        assertThat(slack).isNotNull();
        assertThat(slack).isEqualTo(beginMessage()
                .inChannel()
                .addAttachment()
                .withAuthorName(DEFAULT_USER_NAME)
                .withMarkdownText(BOLD + "<any expression>" + BOLD)
                .endAttachment()
                .build());
    }

    @Test
    void dice() throws Exception {
        // Given
        context = context.withInput("2d6");
        Expression expression = new DiceResult(2, 6, 12);

        // When
        SlackMessage slack = slackMessage(expression);

        // Then
        assertThat(slack).isNotNull();
        assertThat(slack).isEqualTo(beginMessage()
                .inChannel()
                .addAttachment()
                .withAuthorName(DEFAULT_USER_NAME)
                .withMarkdownText("2d6= *12*")
                .endAttachment()
                .build());
    }

    @Test
    void multiple_commands_should_be_consistent() throws Exception {
        // Given
        context = context.withInput("2d6");
        Expression expression = text("some text:").then(new DiceResult(2, 6, 12));

        // When
        SlackMessage slack = slackMessage(expression);

        // Then
        assertThat(slack).isNotNull();
        assertThat(slack).isEqualTo(beginMessage()
                .inChannel()
                .addAttachment()
                .withAuthorName(DEFAULT_USER_NAME)
                .withMarkdownText("2d6= *some text: 12*")
                .endAttachment()
                .build());
    }

    private SlackMessage slackMessage(Expression expression) {
        SlackRendererVisitor builder = new SlackRendererVisitor(context);
        builder = builder.visit(expression);
        return builder.message();
    }

}
