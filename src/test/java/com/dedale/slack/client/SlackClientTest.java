package com.dedale.slack.client;

import com.dedale.slack.SlackCredentials;
import com.dedale.slack.request.SlackRequestBuilder;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.inject.Inject;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@MicronautTest
@ExtendWith(MockitoExtension.class)
class SlackClientTest {

    private static final String BOT_TOKEN = "bot_token";


    //    @Rule
//    public MockitoRule rule = MockitoJUnit.rule();
//
//    @Mock
//    private HttpClient client;
//
//    @Mock
//    private WebTarget webTarget;
//
//    @Mock
//    private Builder builder;
//
//
//    private SlackCredentials credentials = Resources.getRelativeTo(SlackCredentials.class, "slack.credentials.sample.yaml").fromYaml().as(SlackCredentials.class);

    @Client(SlackClient.SLACK_API)
    @Inject
    private HttpClient client;

    @Captor
    private ArgumentCaptor<HttpRequest<SlackRequestBuilder.Form>> entity;

    private SlackCredentials credentials = new SlackCredentials();

    private SlackClient slackClient;

    @BeforeEach
    void initializeWebClient() {
        client = spy(client);
        slackClient = new SlackClient(client, credentials);
    }

    @Test
    void post_on_slack_chat_postMessage_url() {
        slackClient.chat().postMessage("a message").send();

        HttpRequest<SlackRequestBuilder.Form> request = verifyRequest();
        assertThat(request.getPath())
                .isEqualTo("chat.postMessage");
    }


    @Test
    void post_credential_token() {
        credentials.setTokens(Map.of("bot", BOT_TOKEN));

        slackClient.chat().postMessage("a message").send();

        assertPostedParameterContainsExactly("token", BOT_TOKEN);
    }

    @Test
    void post_channel() {
        credentials.setDefaultChannel("general");

        slackClient.chat().postMessage("a message").send();

        assertPostedParameterContainsExactly("channel", "general");
    }

    @Test
    void post_on_technical_channel() {
        credentials.setTechnicalChannel("a_technical_channel");

        slackClient.technicalChat().postMessage("a message").send();

        assertPostedParameterContainsExactly("channel", "a_technical_channel");
    }

    @Test
    void post_message() {
        slackClient.chat().postMessage("a message").send();

        assertPostedParameterContainsExactly("text", "a message");
    }

    //
    // Utils
    //

    private HttpRequest<SlackRequestBuilder.Form> verifyRequest() {
        verify(client).exchange(entity.capture());
        return entity.getValue();
    }

    private void assertPostedParameterContainsExactly(String parameterName, String parameterValue) {
        assertThat(verifyRequest())
                .satisfies(request ->
                        assertThat(request.getBody().get().get(parameterName)).isEqualTo(parameterValue));
    }


}
