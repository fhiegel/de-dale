package com.dedale.slack.client.response.legacy;

import java.util.List;

public class SlackResponseBuilder {

    private SlackResponse bean = new SlackResponse();

    public static SlackResponseBuilder beginResponse() {
        return new SlackResponseBuilder();
    }

    // Text

    public SlackResponseBuilder withText(String text) {
        bean.setText(text);
        return this;
    }

    // ResponseType

    public SlackResponseBuilder inChannel() {
        return withResponseType(SlackResponseType.IN_CHANNEL);
    }

    public SlackResponseBuilder ephemeralResponse() {
        return withResponseType(SlackResponseType.EPHEMERAL);
    }

    private SlackResponseBuilder withResponseType(SlackResponseType responseType) {
        bean.setResponseType(responseType);
        return this;
    }

    // Attachments

    public SlackResponseAttachmentBuilder<SlackResponseBuilder> addAttachment() {
        return new SlackResponseAttachmentBuilder<>(this::addAttachment, this);
        
    }

    private SlackResponseBuilder addAttachment(SlackResponseAttachment attachment) {
        bean.addAttachment(attachment);
        return this;
    }

    public SlackResponseBuilder withAttachments(List<SlackResponseAttachment> attachments) {
        bean.setAttachments(attachments);
        return this;
    }

    // Build in methods

    public SlackResponse build() {
        return bean;
    }

}
