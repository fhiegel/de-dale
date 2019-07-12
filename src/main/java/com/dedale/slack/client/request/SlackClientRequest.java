package com.dedale.slack.client.request;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SlackClientRequest {
    static final String CHANNEL_PARAM = "channel";
    static final String TEXT_PARAM = "text";
    static final String TOKEN_PARAM = "token";
    static final String USER_PARAM = "user";

    private final Map<String, String> requestAsForm = new HashMap<>();

    SlackClientRequest(){
    }

    public SlackClientRequest withToken(String token) {
        return addParam(TOKEN_PARAM, token);
    }

    public SlackClientRequest withText(String text) {
        return addParam(TEXT_PARAM, text);
    }

    public SlackClientRequest withChannel(String channel) {
        return addParam(CHANNEL_PARAM, channel);
    }

    public SlackClientRequest withUser(String user) {
        return addParam(USER_PARAM, user);
    }

    public SlackClientRequest withCustom(String key, String value) {
        return addParam(key, value);
    }

    private SlackClientRequest addParam(String name, String value) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(value);
        requestAsForm.put(name, value);
        return this;
    }

    public Map<String, String> asMap() {
        return Map.copyOf(requestAsForm);
    }

}
