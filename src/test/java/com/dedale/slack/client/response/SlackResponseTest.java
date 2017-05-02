package com.dedale.slack.client.response;

import static com.dedale.utils.JsonUtils.asJson;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.dedale.utils.FileTestUtils;

public class SlackResponseTest {

    @Test
    public void should_slack_response_be_ephemeral_by_default() throws Exception {
        // Given
        SlackResponse response = SlackResponseBuilder.beginResponse().build();

        // Then
        assertResponseEqualsFile(response, "empty_ephemeral_response.json");
    }

    @Test
    public void should_slack_response_be_in_channel() throws Exception {
        // Given
        SlackResponse response = SlackResponseBuilder.beginResponse().inChannel().build();

        // Then
        assertResponseEqualsFile(response, "empty_in_channel_response.json");
    }

    @Test
    public void should_slack_response_contains_text() throws Exception {
        // Given
        SlackResponse response = SlackResponseBuilder.beginResponse().withText("some text here").build();

        // Then
        assertResponseEqualsFile(response, "simple_ephemeral_response.json");
    }
    
    @Test
    public void should_slack_response_toString_be_pretty() throws Exception {
        // Given
        SlackResponse response = SlackResponseBuilder.beginResponse().withText("some text here").build();

        // Then
        String expectedToString = FileTestUtils.getResourceFileAsString(getClass(), "response_toString");
        assertThat(response.toString()).isEqualTo(expectedToString);
    }

    //
    // Utilities
    //

    private void assertResponseEqualsFile(SlackResponse response, String filePath) {
        String expected = FileTestUtils.getResourceFileAsJson(getClass(), filePath);
        assertThat(asJson(response)).isEqualTo(expected);
    }

}
