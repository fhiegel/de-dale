package com.dedale.slack.client;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.inject.Inject;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@MicronautTest
@ExtendWith(MockitoExtension.class)
class SlackClientTest {

    private static final String BOT_TOKEN = "bot_token";

    @Client("/")
    @Inject
    private RxHttpClient client;

    @Captor
    private ArgumentCaptor<HttpRequest<Map>> entity;

    private SlackCredentials credentials = new SlackCredentials();

    private SlackClient slackClient;

    @BeforeEach
    void initializeWebClient() {
        client = spy(client);
        credentials.setTokens(Map.of(SlackCredentials.BOT_TOKEN, BOT_TOKEN));
        slackClient = new SlackClient(client, credentials, "general", "a_technical_channel");
    }

    @Test
    void post_on_slack_chat_postMessage_url() {
        slackClient.chat().postMessage("a message").post();

        HttpRequest<Map> request = verifyPOSTRequest();
        assertThat(request.getPath())
                .isEqualTo("chat.postMessage");
    }


    @Test
    void post_credential_token() {
        slackClient.chat().postMessage("a message").post();

        assertPostedParameterContainsExactly("token", BOT_TOKEN);
    }

    @Test
    void post_channel() {
        slackClient.chat().postMessage("a message").post();

        assertPostedParameterContainsExactly("channel", "general");
    }

    @Test
    void post_on_technical_channel() {
        slackClient.chat().onTechnical().postMessage("a message").post();

        assertPostedParameterContainsExactly("channel", "a_technical_channel");
    }

    @Test
    void post_message() {
        slackClient.chat().postMessage("a message").post();

        assertPostedParameterContainsExactly("text", "a message");
    }

    //
    // Utils
    //

    private HttpRequest<Map> verifyPOSTRequest() {
        verify(client).exchange(entity.capture(), eq(String.class));
        return entity.getValue();
    }

    private void assertPostedParameterContainsExactly(String parameterName, String parameterValue) {
        assertThat(verifyPOSTRequest())
                .satisfies(request ->
                        assertThat(request.getBody().get().get(parameterName)).isEqualTo(parameterValue));
    }


}
