package com.dedale.slack.server.event;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller(value = "/slack/listener")
@Validated
public class SlackEventListener {

    private static final Logger log = Logger.getLogger(SlackEventListener.class.getName());

    private final SlackEventHandler handler;

    SlackEventListener(SlackEventHandler handler) {
        this.handler = handler;
    }

    @Post
    public SlackEventResponse listen(@Valid SlackEvent event) {
        log.fine(event.toString());
        handler.handle(event);
        return new SlackEventResponse(event.getChallenge());
    }

}
