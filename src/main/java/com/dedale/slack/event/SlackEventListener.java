package com.dedale.slack.event;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

import java.util.logging.Logger;

@Controller("/slack/listener")
public class SlackEventListener {

    private static final Logger log = Logger.getLogger(SlackEventListener.class.getName());

    @Post
    public String listen(@Body String request) {
        log.info(request);
        return request;
    }

}
