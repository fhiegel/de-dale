package com.dedale.slack.client;

import javax.ws.rs.client.Client;

import com.dedale.slack.request.SlackRequestBuilder;

public class SlackClientOnChat {

    private final Client client;
    private final SlackRequestBuilder requestBuilder;

    SlackClientOnChat(Client client, SlackRequestBuilder requestBuilder) {
        this.client = client;
        this.requestBuilder = requestBuilder;
    }

    public SlackClientSender postMessage(String message) {
        return new SlackClientSender(client, "chat.postMessage", requestBuilder.withText(message));
    }

}