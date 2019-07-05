package com.dedale.slack.api.hermes;

import com.dedale.core.engine.ExecutionContext;
import com.dedale.core.engine.InterpreterEngine;
import com.dedale.core.engine.expression.Expression;
import com.dedale.slack.api.SlackRendererVisitor;
import com.dedale.slack.message.SlackMessage;
import com.dedale.slack.request.SlackRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

import javax.inject.Inject;

@Controller("slack/hermes")
public class HermesSlackClient {
    
    private final InterpreterEngine calculator;

    @Inject
    public HermesSlackClient(InterpreterEngine calculator) {
        this.calculator = calculator;
    }

    @Post(consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public SlackMessage slackRoll(SlackRequest slackRequest) {
        ExecutionContext context = calculator
                .defaultContext()
                .withUser(slackRequest.getUserId())
                .withUserName(slackRequest.getUserName())
                .withInput(slackRequest.getText());

        Expression expression = calculator.interpret(context);

        return mapToMessage(context, expression);
    }

    private SlackMessage mapToMessage(ExecutionContext context, Expression expression) {
        SlackRendererVisitor builder = new SlackRendererVisitor(context);
        builder = builder.visit(expression);
        return builder.message();
    }
    
}
