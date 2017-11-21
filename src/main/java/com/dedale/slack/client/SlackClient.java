package com.dedale.slack.client;

import javax.inject.Inject;
import javax.ws.rs.client.Client;

import org.glassfish.jersey.client.JerseyClientBuilder;

import com.dedale.slack.SlackCredentials;
import com.dedale.slack.request.SlackRequestBuilder;

public class SlackClient {

    static final String SLACK_API = "https://slack.com/api/";

    private SlackCredentials credentials;
    private Client client;

    @Inject
    public SlackClient(SlackCredentials credentials) {
        this(JerseyClientBuilder.newClient(), credentials);
    }

    SlackClient(Client client, SlackCredentials credentials) {
        this.client = client;
        this.credentials = credentials;
    }

    public SlackClientOnChat chat() {
        return chatOn(credentials.getDefaultChannel());
    }
    
    public SlackClientOnChat technicalChat() {
        return chatOn(credentials.getTechnicalChannel());
    }

    public SlackClientOnChat chatOn(String channelName) {
        SlackRequestBuilder requestBuilder = newBotParameters();
        return new SlackClientOnChat(client, requestBuilder.withChannel(channelName));
    }

    private SlackRequestBuilder newBotParameters() {
        return new SlackRequestBuilder().withToken(getBotToken());
    }

    private String getBotToken() {
        return credentials.getToken("bot").orElseThrow(() -> new IllegalArgumentException("No credentials provided for bot"));
    }


}
