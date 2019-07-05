package com.dedale.slack.message;

import static com.dedale.utils.JsonUtils.asJson;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.dedale.slack.message.SlackMessage;
import com.dedale.slack.message.SlackMessageBuilder;
import com.dedale.utils.FileTestUtils;

class SlackMessageTest {

    @Test
    void empty_message_is_ephemeral() {
        // Given
        SlackMessage response = SlackMessageBuilder.beginMessage().build();

        // Then
        assertResponseEqualsFile(response, "empty_ephemeral.json");
    }

    @Test
    void empty_in_channel_message() {
        // Given
        SlackMessage response = SlackMessageBuilder.beginMessage().inChannel().build();

        // Then
        assertResponseEqualsFile(response, "empty_in_channel.json");
    }

    @Test
    void should_slack_response_contains_text() {
        // Given
        SlackMessage response = SlackMessageBuilder.beginMessage().withText("some text here").build();

        // Then
        assertResponseEqualsFile(response, "simple_ephemeral.json");
    }

    @Test
    void message_toString_should_be_pretty() {
        // Given
        SlackMessage response = SlackMessageBuilder.beginMessage().withText("some text here").build();

        // Then
        String expectedToString = FileTestUtils.getResourceFileAsString(getClass(), "message_toString");
        assertThat(response.toString()).isEqualTo(expectedToString);
    }

    @Test
    void should_single_attachment_response_have_all_fields() {
        // Given
        SlackMessage response = SlackMessageBuilder.beginMessage()
                .addAttachment()
                .withFallback("Required plain-text summary of the attachment.")
                .withColor("#36a64f")
                .withPretext("Optional text that appears above the attachment block")
                .withAuthorName("Bobby Tables")
                .withAuthorLink("http://flickr.com/bobby/")
                .withAuthorIcon("http://flickr.com/icons/bobby.jpg")
                .markdownInText().markdownInPretext()
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
        assertResponseEqualsFile(response, "single_attachment.json");
    }

    //
    // Utilities
    //

    private void assertResponseEqualsFile(SlackMessage response, String filePath) {
        String expected = FileTestUtils.getResourceFileAsJson(getClass(), filePath);
        assertThat(asJson(response)).isEqualTo(expected);
    }

}
