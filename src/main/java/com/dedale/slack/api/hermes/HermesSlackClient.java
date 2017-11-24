package com.dedale.slack.api.hermes;

import static com.dedale.markdown.MarkdownTags.MARKDOWN_BOLD;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.dedale.core.calculator.CalculatingFeatures;
import com.dedale.core.engine.InterpreterEngine;
import com.dedale.core.engine.expression.Expression;
import com.dedale.core.engine.expression.ExpressionPrinter;
import com.dedale.slack.message.SlackMessage;
import com.dedale.slack.message.SlackMessageBuilder;
import com.dedale.slack.request.SlackRequest;

@Path("slack/hermes")
public class HermesSlackClient {
    
    @Inject
    @Named(CalculatingFeatures.ENGINE)
    private InterpreterEngine calculator;
    

    @POST
    public SlackMessage slackRoll(SlackRequest slackRequest) {
        String diceSentence = slackRequest.getText();
        String diceResult = calculate(diceSentence);

        String responseText = slackRequest.getText() + "= " + MARKDOWN_BOLD + diceResult + MARKDOWN_BOLD;

        return SlackMessageBuilder
                .beginResponse()
                .inChannel()
                    .addAttachment()
                        .withAuthorName(slackRequest.getUserName())
                        .withMarkdownText(responseText)
                    .endAttachment()
                .build();
    }
    
    private String calculate(String input) {
        Expression expression = interpret(input);
        return print(expression);
    }

    private Expression interpret(String input) {
        return calculator.interpret(input);
    }

    private String print(Expression expression) {
        ExpressionPrinter printer = new ExpressionPrinter();
        expression.accept(printer);
        return printer.print();
    }
    
}
