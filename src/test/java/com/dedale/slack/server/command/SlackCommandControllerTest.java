package com.dedale.slack.server.command;

import com.dedale.slack.SlackTestUtils;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @see SlackCommandController
 */
@MicronautTest
class SlackCommandControllerTest {

    @Inject
    @Client("/slack/cmd")
    private HttpClient client;

    @Test
    void command_invoked() {
        SlackCommand commandRequest = SlackTestUtils.beginRequest().withText("command line").asRequest();

        String responseMsg = client.toBlocking()
                .retrieve(HttpRequest.POST("/", commandRequest)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED));

        assertThat(responseMsg).containsPattern("\"text\":\".*command line= \\*ok\\*\"");
    }

}
