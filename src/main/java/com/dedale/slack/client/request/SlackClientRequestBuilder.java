package com.dedale.slack.client.request;

import io.micronaut.http.client.RxHttpClient;

public class SlackClientRequestBuilder extends AbstractRequestBuilder<SlackClientRequestBuilder> {

    private SlackClientRequestBuilder(RxHttpClient client, SlackClientRequest request) {
        super(client, request);
    }

    public static SlackClientRequestBuilder forBot(RxHttpClient client, String botToken) {
        return new SlackClientRequestBuilder(client, aRequestWithToken(botToken));
    }

    private static SlackClientRequest aRequestWithToken(String token) {
        return new SlackClientRequest().withToken(token);
    }

    public Chat chat(String defaultChannel, String technicalChannel) {
        return new Chat(this, defaultChannel, technicalChannel).onDefault();
    }

    public Conversations conversations() {
        return new Conversations(this);
    }

    public Users users() {
        return new Users(this);
    }


}
