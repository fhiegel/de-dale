package com.dedale.api;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dedale.calculator.StringCalculator;
import com.dedale.slack.client.request.SlackRequest;
import com.dedale.slack.client.response.SlackResponse;
import com.dedale.slack.client.response.SlackResponseBuilder;

@Path("dices")
public class DiceResource {

    private static final String MARKDOWN_BOLD = "*";

    @Inject
    private StringCalculator calculator;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String defaultRoll(String diceSentence) {
        return calculator.calculate(diceSentence).toString();
    }

    @POST
    @Path("/slack")
    public SlackResponse slackRoll(SlackRequest slackRequest) {
        String diceSentence = slackRequest.getText();
        String diceResult = calculator.calculate(diceSentence);

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
