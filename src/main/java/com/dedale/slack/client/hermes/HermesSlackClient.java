package com.dedale.slack.client.hermes;

import static com.dedale.markdown.MarkdownTags.MARKDOWN_BOLD;
import static com.dedale.markdown.MarkdownTags.MARKDOWN_CODE;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.dedale.calculator.StringCalculator;
import com.dedale.calculator.StringCalculatorInputValidator;
import com.dedale.slack.client.request.SlackRequest;
import com.dedale.slack.client.response.SlackResponse;
import com.dedale.slack.client.response.SlackResponseBuilder;

@Path("slack/hermes")
public class HermesSlackClient {
    
    @Inject
    private StringCalculator calculator;
    
    @Inject
    private StringCalculatorInputValidator validator;

    @POST
    public SlackResponse slackRoll(SlackRequest slackRequest) {
        String diceSentence = slackRequest.getText();
        if (!validator.validate(diceSentence)) {
            String responseText = "Impossible de lancer la définition de dé suivante : "+ MARKDOWN_CODE + slackRequest.getText() + MARKDOWN_CODE;
            return SlackResponseBuilder
                    .beginResponse()
                    .inChannel()
                        .addAttachment()
                            .asError()
                            .withColor("#ff0000")
                            .withAuthorName(slackRequest.getUserName())
                            .withMarkdownText(responseText)
                        .endAttachment()
                    .build();
        }
        String diceResult = calculator.calculate(diceSentence).toString();

        String responseText = slackRequest.getText() + "= " + MARKDOWN_BOLD + diceResult + MARKDOWN_BOLD;

        return SlackResponseBuilder
                .beginResponse()
                .inChannel()
                    .addAttachment()
                        .withAuthorName(slackRequest.getUserName())
                        .withMarkdownText(responseText)
                    .endAttachment()
                .build();
    }
    
}
