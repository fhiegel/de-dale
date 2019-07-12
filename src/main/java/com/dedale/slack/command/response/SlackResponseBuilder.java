package com.dedale.slack.command.response;

import java.util.List;


public class SlackResponseBuilder {

    private SlackResponse response = new SlackResponse();

    public static SlackResponseBuilder aResponse() {
        return new SlackResponseBuilder();
    }

    // Text

    public SlackResponseBuilder withText(Object object) {
        return withText(object.toString());
    }


    public SlackResponseBuilder withText(String text) {
        response.setText(text);
        return this;
    }

    // ResponseType

    public SlackResponseBuilder inChannel() {
        return withResponseType(SlackResponseType.IN_CHANNEL);
    }

    public SlackResponseBuilder onlyForUser() {
        return withResponseType(SlackResponseType.EPHEMERAL);
    }

    private SlackResponseBuilder withResponseType(SlackResponseType responseType) {
        response.setResponseType(responseType);
        return this;
    }

    // Attachments

    public SlackAttachmentBuilder<SlackResponseBuilder> addAttachment() {
        return new SlackAttachmentBuilder<>(this::addAttachment, this);

    }

    private SlackResponseBuilder addAttachment(SlackAttachment attachment) {
        response.addAttachment(attachment);
        return this;
    }

    public SlackResponseBuilder withAttachments(List<SlackAttachment> attachments) {
        response.setAttachments(attachments);
        return this;
    }

    // Defaults

    public SlackResponseBuilder withToken(String token) {
        response.setToken(token);
        return this;
    }

    public SlackResponseBuilder withChannel(String channel) {
        response.setChannel(channel);
        return this;
    }

    private SlackResponseBuilder asMarkdown() {
        return this;
    }

    // Build in methods

    public SlackResponse build() {
        return response;
    }

}
