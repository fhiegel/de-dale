package com.dedale.slack.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;

import com.dedale.slack.request.SlackRequestBuilder;

public class SlackClientSender {
    private Client client;
    private String method;
    private SlackRequestBuilder requestBuilder;

    SlackClientSender(Client client, String string, SlackRequestBuilder requestBuilder) {
        this.client = client;
        this.method = string;
        this.requestBuilder = requestBuilder;
    }

    public void send() {
        client.target(SlackClient.SLACK_API + method).request().post(Entity.form(requestBuilder.build()));
        System.out.println("SEND");
    }
}