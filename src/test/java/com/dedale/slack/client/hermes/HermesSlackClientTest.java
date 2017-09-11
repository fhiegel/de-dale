package com.dedale.slack.client.hermes;

import static com.dedale.slack.client.SlackTestUtils.getResponseContentAsString;
import static com.dedale.utils.FileTestUtils.getResourceFileAsJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.mockito.Mockito;

import com.dedale.hermes.HermesEngine;
import com.dedale.hermes.HermesTokenizer;
import com.dedale.slack.client.SlackTestUtils;
import com.dedale.slack.client.request.SlackRequestReader;
import com.dedale.slack.client.response.SlackResponseWriter;

public class HermesSlackClientTest extends JerseyTest {

    private HermesEngine hermes;

    @Override
    protected Application configure() {
        return new ResourceConfig() {
            {
                hermes = spy(HermesEngine.class);
                
                register(SlackRequestReader.class);
                register(SlackResponseWriter.class);
                register(new HermesSlackClient(hermes));
            }
        };
    }

    @Test
    public void should_return_200_for_a_correct_slack_input() throws Exception {
        // Given
        Form form = SlackTestUtils.defaultSlackRequest();

        Response response = postHermes(form);

        // Then
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void should_ask_hermes_engine() throws Exception {
        // Given
        Form form = SlackTestUtils.defaultSlackRequest();

        // When
        postHermes(form);

        // Then
        Mockito.verify(hermes).dispatch(any(HermesTokenizer.class));
    }

    @Test
    public void should_print_response_in_channel() throws Exception {
        // Given
        Form form = SlackTestUtils.defaultSlackRequest();

        // When
        Response response = postHermes(form);

        // Then
        assertResponseEqualsFile(response, "hermes_response.json");
    }

    @Test
    public void should_print_response_with_error_message_in_channel_when_request_is_invalid() throws Exception {
        // Given
        Form form = SlackTestUtils.beginRequest().withUserName("Dummy User").withText("not a dice Sentense").build();

        // When
        Response response = postHermes(form);

        // Then
        assertResponseEqualsFile(response, "hermes_response_for_invalid_request.json");
    }

    //
    // Utilitaires
    //

    private Response postHermes(Form form) {
        return target("slack/hermes").request().post(Entity.form(form));
    }

    private void assertResponseEqualsFile(Response response, String filePath) throws Exception {
        String responseContent = getResponseContentAsString(response);
        String expectedFileContent = getResourceFileAsJson(getClass(), filePath);
        assertThat(responseContent).isEqualTo(expectedFileContent);
    }

}
