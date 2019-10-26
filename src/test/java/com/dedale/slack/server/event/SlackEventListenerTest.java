package com.dedale.slack.server.event;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.UUID;
import java.util.stream.Stream;

import static io.micronaut.http.HttpRequest.POST;
import static io.micronaut.http.MediaType.APPLICATION_JSON;
import static io.micronaut.http.MediaType.APPLICATION_JSON_TYPE;
import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
class SlackEventListenerTest {

    @Inject
    @Client("/slack/listener")
    private HttpClient eventClient;

    @MockBean(SlackEventHandler.class)
    SlackEventHandler mockHandler() {
        return Mockito.mock(SlackEventHandler.class);
    }

    @ParameterizedTest
    @MethodSource("getRandomChallenge")
    void challenge_is_always_accepted(String challenge) {
        SlackEvent event = new SlackEvent();
        event.setChallenge(challenge);

        String response = postEvent(event);

        assertThat(response)
                .containsPattern(String.format("\"challenge\":\"%s\"", challenge));
    }

    private String postEvent(SlackEvent event) {
        return eventClient.toBlocking()
                .retrieve(POST("/", event));
    }

    private static Stream<String> getRandomChallenge() {
        return Stream.of(UUID.randomUUID().toString());
    }

}
