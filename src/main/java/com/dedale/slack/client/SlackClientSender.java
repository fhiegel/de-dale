package com.dedale.slack.client;

import com.dedale.slack.request.SlackRequestBuilder;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;

public class SlackClientSender {
    private HttpClient client;
    private String method;
    private SlackRequestBuilder requestBuilder;

    SlackClientSender(HttpClient client, String string, SlackRequestBuilder requestBuilder) {
        this.client = client;
        this.method = string;
        this.requestBuilder = requestBuilder;
    }

    public void send() {
        client.exchange(
                HttpRequest.POST(method, requestBuilder.build())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED));
        System.out.println("SEND");
    }
}