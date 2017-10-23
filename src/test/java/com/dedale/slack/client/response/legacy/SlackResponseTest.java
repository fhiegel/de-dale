package com.dedale.slack.client.response.legacy;

import static com.dedale.utils.JsonUtils.asJson;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

import com.dedale.slack.client.response.legacy.SlackResponse;
import com.dedale.slack.client.response.legacy.SlackResponseBuilder;
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

    @Test
    public void should_single_attachment_response_have_all_fields() throws Exception {
        // Given
        SlackResponse response = SlackResponseBuilder.beginResponse()
                .addAttachment()
                    .withFallback("Required plain-text summary of the attachment.")
                    .withColor("#36a64f")
                    .withPretext("Optional text that appears above the attachment block")
                    .withAuthorName("Bobby Tables")
                    .withAuthorLink("http://flickr.com/bobby/")
                    .withAuthorIcon("http://flickr.com/icons/bobby.jpg")
                    .withTitle("Slack API Documentation")
                    .withTitleLink("https://api.slack.com/")
                    .withText("Optional text that appears within the attachment")
                        .addField()
                            .withTitle("Priority")
                            .withValue("High")
                        .endField()
                    .withImageUrl("http://my-website.com/path/to/image.jpg")
                    .withThumbUrl("http://example.com/path/to/thumb.png")
                    .withFooter("Slack API")
                    .withFooterIcon("https://platform.slack-edge.com/img/default_application_icon.png")
                    .withTimestamp(new Date(123456789L))
                    .endAttachment()
                
                .build();

        // Then
        assertResponseEqualsFile(response, "single_attachment_response.json");
    }

    //
    // Utilities
    //

    private void assertResponseEqualsFile(SlackResponse response, String filePath) {
        String expected = FileTestUtils.getResourceFileAsJson(getClass(), filePath);
        assertThat(asJson(response)).isEqualTo(expected);
    }

}
