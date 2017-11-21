package com.dedale.slack.message;

import java.util.List;

public class SlackMessageBuilder {

    private SlackMessage message = new SlackMessage();

    public static SlackMessageBuilder beginResponse() {
        return new SlackMessageBuilder();
    }

    // Text

    public SlackMessageBuilder withText(String text) {
        message.setText(text);
        return this;
    }

    // ResponseType

    public SlackMessageBuilder inChannel() {
        return withResponseType(SlackResponseType.IN_CHANNEL);
    }

    public SlackMessageBuilder ephemeralResponse() {
        return withResponseType(SlackResponseType.EPHEMERAL);
    }

    private SlackMessageBuilder withResponseType(SlackResponseType responseType) {
        message.setResponseType(responseType);
        return this;
    }

    // Attachments

    public SlackMessageAttachmentBuilder<SlackMessageBuilder> addAttachment() {
        return new SlackMessageAttachmentBuilder<>(this::addAttachment, this);

    }

    private SlackMessageBuilder addAttachment(SlackMessageAttachment attachment) {
        message.addAttachment(attachment);
        return this;
    }

    public SlackMessageBuilder withAttachments(List<SlackMessageAttachment> attachments) {
        message.setAttachments(attachments);
        return this;
    }

    // Defautls

    public SlackMessageBuilder withToken(String token) {
        message.setToken(token);
        return this;
    }

    public SlackMessageBuilder withChannel(String channel) {
        message.setChannel(channel);
        return this;
    }

    // Build in methods

    public SlackMessage build() {
        return message;
    }

}
