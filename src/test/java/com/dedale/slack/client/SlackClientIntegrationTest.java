package com.dedale.slack.client;

import io.micronaut.http.HttpResponse;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@MicronautTest(environments = "secrets")
class SlackClientIntegrationTest {

    static final String MY_CHANNEL = "D749ZJ890";
    static final String ME = "U0A48EDPD";

    @Inject
    private SlackClient slackClient;

    @Test
    void smoke() {
        assertThat(slackClient).isNotNull();
    }

    @Test
    void get_conversations() {
        String conversations = slackClient.conversations()
                .list()
                .appendToRequest(builder -> builder.withCustom("types", "mpim,im"))
                .get()
                .blockingFirst();

        assertThat(conversations)
                .containsPattern("\"ok\":true")
                .containsPattern("\"channels\":\\[(\\{.*)+]");
    }

    @Test
    void get_users() {
        String users = slackClient.users()
                .list()
                .get()
                .blockingFirst();

        assertThat(users)
                .containsPattern("\"ok\":true")
                .containsPattern("\"members\":\\[(\\{.*)+]");
    }

    @Test
    void get_users_conversations() {
        String users = slackClient.users()
                .conversations()
                .appendToRequest(request -> request.withCustom("user", ME)
                        .withCustom("types", "public_channel,private_channel,mpim"))
                .get()
                .blockingFirst();

        assertThat(users)
                .containsPattern("\"ok\":true")
                .containsPattern("\"channels\":\\[(\\{.*)+]");
    }

    @Test
    void post_message_to_user() {
        HttpResponse<String> response = slackClient.chat()
                .channel(MY_CHANNEL)
                .postMessage("Still alive!")
                .post()
                .blockingFirst();

        assertThat(response.body())
                .containsPattern("\"ok\":true")
                .containsPattern("\"channel\":\"D749ZJ890\"")
                .containsPattern("\"text\":\"Still alive!\"");
    }

    @Test
    void post_message_to_technical_channel() {
        HttpResponse<String> response = slackClient.chat()
                .onTechnical()
                .postEphemeral("Still alive!", ME)
                .post()
                .blockingFirst();

        assertThat(response.body())
                .containsPattern("\"ok\":true");
    }

    @Test
    @Disabled
    void post_message_to_default_channel() {
        HttpResponse<String> response = slackClient.chat()
                .postMessage("plop")
                .post()
                .blockingFirst();

        assertThat(response.body())
                .containsPattern("\"ok\":true")
                .containsPattern("\"channel\":\"C0A48J0TH\"")
                .containsPattern("\"text\":\"plop\"");
    }

    @Test
    void post_ephemeral_message_to_default_channel() {
        HttpResponse<String> response = slackClient.chat()
                .postEphemeral("Still alive!", ME)
                .post()
                .blockingFirst();

        assertThat(response.body())
                .containsPattern("\"ok\":true");
    }

}
