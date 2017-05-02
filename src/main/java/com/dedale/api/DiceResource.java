package com.dedale.api;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dedale.calculator.StringCalculator;
import com.dedale.calculator.StringCalculatorInputValidator;
import com.dedale.slack.client.request.SlackRequest;
import com.dedale.slack.client.response.SlackResponse;
import com.dedale.slack.client.response.SlackResponseBuilder;

@Path("dices")
public class DiceResource {

    private static final String MARKDOWN_CODE = "`";
    private static final String MARKDOWN_PRE = "```";
    private static final String MARKDOWN_ITALIC = "_";
    private static final String MARKDOWN_BOLD = "*";

    @Inject
    private StringCalculator calculator;
    @Inject
    private StringCalculatorInputValidator validator;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String defaultRoll(String diceSentence) {
        if (!validator.validate(diceSentence)) {
            throw new WebApplicationException("Cannot parse dice sentence. sentence:" + diceSentence,
                    Response.Status.BAD_REQUEST);
        }
        return calculator.calculate(diceSentence).toString();
    }

    @POST
    @Path("/slack")
    public SlackResponse slackRoll(SlackRequest slackRequest) {
        String diceSentence = slackRequest.getText();
        if (!validator.validate(diceSentence)) {
            String responseText = "Impossible de lancer la définition de dé suivante : "+ MARKDOWN_CODE + slackRequest.getText() + MARKDOWN_CODE;
            return SlackResponseBuilder
                    .beginResponse()
                    .inChannel()
                        .addAttachment()
                            .withColor("#ff0000")
                            .withAuthorName(slackRequest.getUserName())
                            .withText(responseText)
                            .markdownInText()
                        .endAttachment()
                    .build();
        }
        String diceResult = calculator.calculate(diceSentence).toString();

        String responseText = MARKDOWN_BOLD + slackRequest.getText() + "=" + MARKDOWN_BOLD + " " + diceResult;

        return SlackResponseBuilder
                .beginResponse()
                .inChannel()
                    .addAttachment()
                        .withAuthorName(slackRequest.getUserName())
                        .withText(responseText)
                        .markdownInText()
                    .endAttachment()
                .build();
    }

}
