package com.dedale.slack.command;

import com.dedale.slack.SlackTestUtils;
import com.dedale.slack.command.request.SlackCommandRequest;
import com.dedale.slack.command.request.SlackRequestBuilder;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @see {@link SlackCommand}
 */
@MicronautTest
class SlackCommandTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void command_invoked() {
        SlackCommandRequest commandRequest = SlackTestUtils.beginRequest().withText("command line").asRequest();

        String responseMsg = client.toBlocking()
                .retrieve(HttpRequest.POST("slack/cmd", commandRequest));

        assertThat(responseMsg).containsPattern("\"text\":\".*command line= \\*ok\\*\"");
    }

}
