package com.dedale.slack.client.request;

import com.dedale.slack.server.command.response.SlackResponse;

public class Chat extends AbstractRequestMethod<Chat> {

    private static final String POST_MESSAGE = "postMessage";
    private static final String POST_EPHEMERAL = "postEphemeral";
    private static final String ARGUMENT_USER = "user";

    private final String defaultChannel;
    private final String technicalChannel;

    Chat(AbstractRequestBuilder<?> previous, String defaultChannel, String technicalChannel) {
        super(previous, "chat");
        this.defaultChannel = defaultChannel;
        this.technicalChannel = technicalChannel;
    }

    public Chat onDefault() {
        return channel(defaultChannel);
    }

    public Chat onTechnical() {
        return channel(technicalChannel);
    }

    public Chat channel(String channel) {
        return appendToRequest(request -> request.withChannel(channel));
    }

    public POST postMessage(String message) {
        return appendToRequest(request -> request.withText(message))
                .postWithMethod(POST_MESSAGE);
    }

    public POST postEphemeral(SlackResponse message, String user) {
        return appendToRequest(request -> request.withText(message.getText()))
//                .appendToRequest(request -> request.withCustom("attachments", String.valueOf(message.getAttachments())))
                .appendToRequest(request -> request.withUser(user))
                .postWithMethod(POST_EPHEMERAL);
    }

    public POST postEphemeral(String message, String user) {
        return appendToRequest(request -> request.withText(message))
                .appendToRequest(request -> request.withCustom(ARGUMENT_USER, user))
                .postWithMethod(POST_EPHEMERAL);
    }

}