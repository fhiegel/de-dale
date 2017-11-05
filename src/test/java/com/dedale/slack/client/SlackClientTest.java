package com.dedale.slack.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.dedale.slack.SlackCredentials;
import com.dedale.utils.resources.Resources;

public class SlackClientTest {

    private static final String BOT_TOKEN = "bot_token";

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private Client client;

    @Mock
    private WebTarget webTarget;

    @Mock
    private Builder builder;

    @Captor
    private ArgumentCaptor<Entity<Form>> entity;

    private SlackCredentials credentials = Resources.getRelativeTo(SlackCredentials.class, "slack.credentials.sample.yaml").fromYaml().as(SlackCredentials.class);

    @InjectMocks
    private SlackClient slackClient = new SlackClient(client, credentials);

    @Before
    public void initializeWebClient() {
        when(client.target(anyString())).thenReturn(webTarget);
        when(webTarget.request()).thenReturn(builder);
    }

    @Test
    public void post_on_slack_chat_postMessage_url() throws Exception {
        slackClient.chat().postMessage("a message").send();

        verify(client).target("https://slack.com/api/chat.postMessage");
    }

    @Test
    public void post_credential_token() throws Exception {
        slackClient.chat().postMessage("a message").send();

        assertPostedParameterContainsExactly("token", BOT_TOKEN);
    }

    @Test
    public void post_channel() throws Exception {
        slackClient.chat().postMessage("a message").send();

        assertPostedParameterContainsExactly("channel", "a_technical_channel");
    }

    @Test
    public void post_message() throws Exception {
        slackClient.chat().postMessage("a message").send();

        assertPostedParameterContainsExactly("text", "a message");
    }

    //
    // Utils
    //

    private void assertPostedParameterContainsExactly(String parameterName, String... parameterValues) {
        verify(builder).post(entity.capture());
        Form form = entity.getValue().getEntity();
        assertThat(form.asMap().get(parameterName)).containsExactly(parameterValues);
    }

}
