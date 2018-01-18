package com.dedale.slack.message;

import java.util.List;

import com.dedale.markdown.Markdown;

public class SlackMessageBuilder {

    private SlackMessage message = new SlackMessage();

    public static SlackMessageBuilder beginMessage() {
        return new SlackMessageBuilder();
    }

    // Text

    public SlackMessageBuilder withText(Object object) {
        return withText(object.toString());
    }
    
    public SlackMessageBuilder withMarkdown(Markdown markdown) {
        return withText(markdown).asMarkdown();
    }
    

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

    // Defaults

    public SlackMessageBuilder withToken(String token) {
        message.setToken(token);
        return this;
    }

    public SlackMessageBuilder withChannel(String channel) {
        message.setChannel(channel);
        return this;
    }

    private SlackMessageBuilder asMarkdown() {
        return this;
    }
    
    // Build in methods

    public SlackMessage build() {
        return message;
    }

}
