package com.dedale.slack.api;

import com.dedale.slack.message.SlackMessage;
import com.dedale.slack.message.SlackMessageBuilder;
import com.dedale.slack.request.SlackRequest;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

import java.util.logging.Logger;

import static com.dedale.markdown.MarkdownTags.BOLD;

@Controller("/slack")
public class SlackApp {

    private static final Logger log = Logger.getLogger(SlackApp.class.getName());

    @Post
    public String listen(@Body String request) {
        log.info(request);
        return request;
    }

    @Post("cmd")
    public SlackMessage slackRoll(SlackRequest request) {
        if (request == null) {
            return SlackMessageBuilder.beginMessage().withText("ERROR").build();
        }
        log.info(request.toString());
        // String diceResult = calculator.calculate(diceSentence).toString();

        String responseText = request.getText() + "= " + BOLD + "ok" + BOLD;

        return SlackMessageBuilder.beginMessage().withText(responseText).build();
    }
}
