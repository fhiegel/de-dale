package com.dedale.slack.api.hermes;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.dedale.core.calculator.CalculatingFeatures;
import com.dedale.core.engine.ExecutionContext;
import com.dedale.core.engine.InterpreterEngine;
import com.dedale.core.engine.expression.Expression;
import com.dedale.slack.api.SlackRendererVisitor;
import com.dedale.slack.message.SlackMessage;
import com.dedale.slack.request.SlackRequest;

@Path("slack/hermes")
public class HermesSlackClient {
    
    @Inject
    @Named(CalculatingFeatures.ENGINE)
    private InterpreterEngine calculator;

    @POST
    public SlackMessage slackRoll(SlackRequest slackRequest) {
        ExecutionContext context = calculator
                .defaultContext()
                .withUser(slackRequest.getUserId())
                .withUserName(slackRequest.getUserName())
                .input(slackRequest.getText());
        
        Expression expression = calculator.interpret(context, context.input());

        SlackRendererVisitor builder = new SlackRendererVisitor(context);
        expression.accept(builder);
        return builder.message();
    }
    
    
    
}
