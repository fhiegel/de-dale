package com.dedale.slack.server.event;

public class SlackEventResponse {

    private final String challenge;

    SlackEventResponse(String challenge) {
        this.challenge = challenge;
    }

    public String getChallenge() {
        return challenge;
    }

}
