package com.dedale.slack.command;

import com.dedale.slack.command.response.SlackResponse;
import com.dedale.slack.command.response.SlackResponseBuilder;
import com.dedale.slack.command.request.SlackCommandRequest;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

import java.util.logging.Logger;

@Controller("/slack/cmd")
public class SlackCommand {

    private static final Logger log = Logger.getLogger(SlackCommand.class.getName());

    @Post
    public SlackResponse execute(SlackCommandRequest request) {
        if (request == null) {
            return ko();
        }
        log.info(request.toString());

        return ok();
    }

    private SlackResponse ko() {
        return SlackResponseBuilder.aResponse()
                .onlyForUser()
                .withText("ERROR")
                .build();
    }

    private SlackResponse ok() {
        return SlackResponseBuilder.aResponse()
                .onlyForUser()
                .withText(String.format("Bien reçu!"))
                .build();
    }
}
