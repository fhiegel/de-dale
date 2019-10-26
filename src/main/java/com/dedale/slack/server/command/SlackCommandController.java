package com.dedale.slack.server.command;

import com.dedale.slack.server.command.response.SlackResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.logging.Logger;

@Controller(value = "/slack/cmd", consumes = MediaType.APPLICATION_FORM_URLENCODED)
@Validated
public class SlackCommandController {

    private static final Logger log = Logger.getLogger(SlackCommandController.class.getName());

    private final SlackCommandHandler handler;

    SlackCommandController(SlackCommandHandler handler) {
        this.handler = handler;
    }

    @Post
    public SlackResponse execute(@Valid SlackCommand command) {
        log.fine(command.toString());
        return handler.handle(command);
    }

}
