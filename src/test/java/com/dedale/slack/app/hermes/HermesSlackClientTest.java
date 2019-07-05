package com.dedale.slack.app.hermes;

import com.dedale.slack.SlackTestUtils;
import com.dedale.slack.message.SlackMessage;
import com.dedale.slack.request.SlackRequestBuilder.Form;
import com.dedale.utils.JsonUtils;
import com.dedale.utils.resources.Resources;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
class HermesSlackClientTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void should_return_200_for_a_correct_slack_input() {
        // Given
        Form form = SlackTestUtils.defaultSlackRequest();

        HttpResponse<SlackMessage> response = postHermes(form);

        // Then
        Assertions.assertEquals(response.getStatus(), HttpStatus.OK);
    }

    @Test
    void should_print_response_in_channel() throws Exception {
        // Given
        Form form = SlackTestUtils.defaultSlackRequest();

        // When
        SlackMessage slackMessage = client.toBlocking()
                .retrieve(HttpRequest.POST("slack/hermes", form)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED), SlackMessage.class);

        // Then
        assertThat(slackMessage).isEqualTo(getSlackMessage("hermes_response.json"));
    }

    private SlackMessage getSlackMessage(String relativePath) throws java.io.IOException {
        return JsonUtils.objectMapper.readValue(Resources
                .getRelativeTo(getClass(), relativePath)
                .asString(), SlackMessage.class);
    }

    @Test
    @Disabled
    void should_print_response_with_error_message_in_channel_when_request_is_invalid() throws Exception {
        // Given
        Form form = SlackTestUtils.beginRequest().withUserName("Dummy User").withText("not a dice Sentence").build();

        // When
        SlackMessage slackMessage = client.toBlocking()
                .retrieve(HttpRequest.POST("slack/hermes", form)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED), SlackMessage.class);

        // Then
        assertThat(slackMessage).isEqualTo(getSlackMessage("hermes_response.json"));
    }

    //
    // Utilitaires
    //

    private HttpResponse<SlackMessage> postHermes(Form form) {
        return client.toBlocking()
                .exchange(HttpRequest.POST("slack/hermes", form)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED));
    }

}
