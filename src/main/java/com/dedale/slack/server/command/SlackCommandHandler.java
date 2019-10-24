package com.dedale.slack.server.command;

import com.dedale.slack.server.command.response.SlackResponse;
import com.dedale.slack.server.command.response.SlackResponseBuilder;

@FunctionalInterface
public interface SlackCommandHandler {
    SlackResponse handle(SlackCommand command);

    SlackCommandHandler instance = command -> SlackResponseBuilder.aResponse()
            .onlyForUser()
            .withText(command.getText())
            .asRawText()
            .build();
}
