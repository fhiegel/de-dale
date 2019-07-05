package com.dedale.slack.client;

import com.dedale.slack.request.SlackRequestBuilder;
import io.micronaut.http.client.HttpClient;

public class SlackClientOnChat {

    private final HttpClient client;
    private final SlackRequestBuilder requestBuilder;

    SlackClientOnChat(HttpClient client, SlackRequestBuilder requestBuilder) {
        this.client = client;
        this.requestBuilder = requestBuilder;
    }

    public SlackClientSender postMessage(String message) {
        return new SlackClientSender(client, "chat.postMessage", requestBuilder.withText(message));
    }

}