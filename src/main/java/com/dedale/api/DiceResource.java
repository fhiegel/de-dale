package com.dedale.api;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dedale.core.calculator.CalculatingFeatures;
import com.dedale.core.engine.InterpreterEngine;
import com.dedale.core.engine.expression.Expression;
import com.dedale.core.engine.expression.ExpressionPrinter;
import com.dedale.slack.message.SlackMessage;
import com.dedale.slack.message.SlackMessageBuilder;
import com.dedale.slack.request.SlackRequest;

@Path("dices")
public class DiceResource {

    private static final String MARKDOWN_BOLD = "*";

    private final InterpreterEngine calculator;

    @Inject
    public DiceResource(@Named(CalculatingFeatures.ENGINE) InterpreterEngine calculator) {
        this.calculator = calculator;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String defaultRoll(String diceSentence) {
        return calculate(diceSentence);
    }

    @POST
    @Path("/slack")
    @Deprecated
    public SlackMessage slackRoll(SlackRequest slackRequest) {
        String diceSentence = slackRequest.getText();
        String diceResult = calculate(diceSentence);

        String responseText = MARKDOWN_BOLD + slackRequest.getText() + "=" + MARKDOWN_BOLD + " " + diceResult;

        return SlackMessageBuilder
                .beginMessage()
                .inChannel()
                .addAttachment()
                .withAuthorName(slackRequest.getUserName())
                .withText(responseText)
                .markdownInText()
                .endAttachment()
                .build();
    }

    private String calculate(String input) {
        Expression expression = interpret(input);
        return print(expression);
    }

    private Expression interpret(String input) {
        return calculator.interpret(calculator.defaultContext().withInput(input));
    }

    private String print(Expression expression) {
        ExpressionPrinter printer = new ExpressionPrinter();
        expression.accept(printer);
        return printer.print();
    }

}
