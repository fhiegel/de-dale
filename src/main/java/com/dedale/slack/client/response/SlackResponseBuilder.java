package com.dedale.slack.client.response;

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

    public SlackResponseBuilder withAttachments(List<String> attachments) {
        bean.setAttachments(attachments);
        return this;
    }
    
    // Build in methods

    public SlackResponse build() {
        return bean;
    }

}
