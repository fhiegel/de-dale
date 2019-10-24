package com.dedale.slack.server.event;

@FunctionalInterface
public interface SlackEventHandler {
    SlackEventHandler instance = (e) -> {};

    void handle(SlackEvent event);
}
