package com.dedale.slack.api.hermes;

import static com.dedale.markdown.MarkdownTags.MARKDOWN_BOLD;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.dedale.calculator.StringCalculator;
import com.dedale.slack.message.SlackMessage;
import com.dedale.slack.message.SlackMessageBuilder;
import com.dedale.slack.request.SlackRequest;

@Path("slack/hermes")
public class HermesSlackClient {
    
    @Inject
    private StringCalculator calculator;
    

    @POST
    public SlackMessage slackRoll(SlackRequest slackRequest) {
        String diceSentence = slackRequest.getText();
        String diceResult = calculator.calculate(diceSentence).toString();

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
    
}
