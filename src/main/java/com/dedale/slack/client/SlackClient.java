package com.dedale.slack.client;

import com.dedale.slack.SlackCredentials;
import com.dedale.slack.request.SlackRequestBuilder;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;

import javax.inject.Inject;
import java.net.MalformedURLException;
import java.net.URI;

@Prototype
public class SlackClient {

    static final String SLACK_API = "https://slack.com/api/";

    private SlackCredentials credentials;
    private HttpClient client;

//    @Inject
//    public SlackClient(SlackCredentials credentials) {
//        this(getClient(), credentials);
//    }
//
//    private static HttpClient getClient() {
//        try {
//            return HttpClient.create(URI.create(SLACK_API).toURL());
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Inject
    SlackClient(@Client("http://foo.bar.slack.com") HttpClient client, SlackCredentials credentials) {
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
        return credentials.getToken("bot");
    }


}
