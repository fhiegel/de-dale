package com.dedale.api;

import static com.dedale.markdown.MarkdownTags.MARKDOWN_BOLD;
import static com.dedale.markdown.MarkdownTags.MARKDOWN_CODE;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dedale.calculator.StringCalculator;
import com.dedale.calculator.StringCalculatorInputValidator;
import com.dedale.slack.message.SlackMessage;
import com.dedale.slack.message.SlackMessageBuilder;
import com.dedale.slack.request.SlackRequest;

@Path("dices")
public class DiceResource {

    @Inject
    private StringCalculator calculator;

    @Inject
    private StringCalculatorInputValidator validator;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String defaultRoll(String diceSentence) {
        if (!validator.validate(diceSentence)) {
            throw new WebApplicationException("Cannot parse dice sentence. sentence:" + diceSentence, Response.Status.BAD_REQUEST);
        }
        return calculator.calculate(diceSentence).toString();
    }

    @POST
    @Path("/slack")
    @Deprecated
    public SlackMessage slackRoll(SlackRequest slackRequest) {
        String diceSentence = slackRequest.getText();
        if (!validator.validate(diceSentence)) {
            String responseText = "Impossible de lancer la définition de dé suivante : " + MARKDOWN_CODE + slackRequest.getText()
                    + MARKDOWN_CODE;
            return SlackMessageBuilder
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

        return SlackMessageBuilder
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
