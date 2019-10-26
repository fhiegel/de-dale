package com.dedale.slack.server.command;

import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.micronaut.http.HttpRequest.POST;
import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
class SlackCommandControllerTest {

    @Inject
    @Client("/slack/cmd")
    private HttpClient commandClient;

    @MockBean(SlackCommandHandler.class)
    SlackCommandHandler mockHandler() {
        return SlackCommandHandler.instance;
    }

    @Test
    void command_invoked() {
        SlackCommand command = new SlackCommandBuilder()
                .withText("command line")
                .asRequest();

        String response = postCommand(command);

        assertThat(response)
                .containsPattern("\"text\":\".*command line.*\"");
    }

    private String postCommand(SlackCommand command) {
        return commandClient.toBlocking()
                .retrieve(POST("/", command).contentType(MediaType.APPLICATION_FORM_URLENCODED));
    }

}
