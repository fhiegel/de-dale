package com.dedale.slack.app.hermes;

import static com.dedale.slack.SlackTestUtils.getResponseContentAsString;
import static com.dedale.utils.FileTestUtils.getResourceFileAsJson;
import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.dedale.DeDaleResourceConfig;
import com.dedale.slack.SlackTestUtils;

public class HermesSlackClientTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new DeDaleResourceConfig();
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
