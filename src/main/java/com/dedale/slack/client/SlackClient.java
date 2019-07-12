package com.dedale.slack.client;

import com.dedale.slack.SlackCredentials;
import com.dedale.slack.client.request.*;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SlackClient {

    private final RxHttpClient client;
    private final SlackCredentials credentials;
    private final String defaultChannel;
    private final String technicalChannel;

    @Inject
    SlackClient(@Client("${slack.api-url:`https://slack.com/api/`}") RxHttpClient client,
                SlackCredentials credentials,
                @Value("${slack.channels.default}") String defaultChannel,
                @Value("${slack.channels.technical}") String technicalChannel) {
        this.client = client;
        this.credentials = credentials;
        this.defaultChannel = defaultChannel;
        this.technicalChannel = technicalChannel;
    }

    private String getBotToken() {
        return credentials.getToken(SlackCredentials.BOT_TOKEN);
    }

    private SlackClientRequestBuilder aBotRequestBuilder(){
        return SlackClientRequestBuilder.forBot(client, getBotToken());
    }

    public Chat chat() {
        return aBotRequestBuilder().chat(defaultChannel, technicalChannel);
    }

    public Conversations conversations() {
        return aBotRequestBuilder().conversations();
    }

    public Users users() {
        return aBotRequestBuilder().users();
    }

}
