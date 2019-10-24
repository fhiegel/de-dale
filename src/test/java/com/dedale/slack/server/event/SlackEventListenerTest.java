package com.dedale.slack.server.event;

import com.dedale.utils.resources.Resources;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.micronaut.http.MediaType.APPLICATION_JSON_TYPE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @see SlackEventListener
 */
@MicronautTest
class SlackEventListenerTest {

    @Inject
    @Client("/slack/listener")
    private HttpClient client;

    @Test
    void challenge_accepted() {
        String challenge = Resources.getRelativeTo(getClass(), "challenge.json").asString();

        String responseMsg = client.toBlocking()
                .retrieve(HttpRequest.POST("slack", challenge)
                                .contentType(APPLICATION_JSON_TYPE)
                                .accept(APPLICATION_JSON_TYPE));
        assertThat(responseMsg).containsPattern("\"challenge\":\"\\S+\"");
    }


}
