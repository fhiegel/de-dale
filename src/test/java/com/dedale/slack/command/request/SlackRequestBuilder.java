package com.dedale.slack.command.request;

import java.util.HashMap;
import java.util.Map;

public class SlackRequestBuilder {

    private Form requestAsForm = new Form();

    public SlackRequestBuilder withText(String text) {
        requestAsForm.param(SlackCommandRequest.TEXT_PARAM, text);
        return this;
    }

    public SlackRequestBuilder withTeamId(String teamId) {
        requestAsForm.param(SlackCommandRequest.TEAM_ID_PARAM, teamId);
        return this;
    }

    public SlackRequestBuilder withTeamDomain(String teamDomain) {
        requestAsForm.param(SlackCommandRequest.TEAM_DOMAIN_PARAM, teamDomain);
        return this;
    }

    public SlackRequestBuilder withChannel(String channel) {
        requestAsForm.param(SlackCommandRequest.CHANNEL_PARAM, channel);
        return this;
    }

    public SlackRequestBuilder withChannelId(String channelId) {
        requestAsForm.param(SlackCommandRequest.CHANNEL_ID_PARAM, channelId);
        return this;
    }

    public SlackRequestBuilder withChannelName(String channelName) {
        requestAsForm.param(SlackCommandRequest.CHANNEL_NAME_PARAM, channelName);
        return this;
    }

    public SlackRequestBuilder withUserId(String userId) {
        requestAsForm.param(SlackCommandRequest.USER_ID_PARAM, userId);
        return this;
    }

    public SlackRequestBuilder withUserName(String userName) {
        requestAsForm.param(SlackCommandRequest.USER_NAME_PARAM, userName);
        return this;
    }

    public SlackRequestBuilder withCommand(String command) {
        requestAsForm.param(SlackCommandRequest.COMMAND_PARAM, command);
        return this;
    }

    public SlackRequestBuilder withResponseUrl(String responseUrl) {
        requestAsForm.param(SlackCommandRequest.RESPONSE_URL_PARAM, responseUrl);
        return this;
    }

    public SlackRequestBuilder withToken(String token) {
        requestAsForm.param(SlackCommandRequest.TOKEN_PARAM, token);
        return this;
    }

    public SlackRequestBuilder withCustom(String key, String value) {
        requestAsForm.param(key, value);
        return this;
    }

    public static class Form extends HashMap<String, String> {
        public void param(String key, String value) {
            put(key, value);
        }
    }

    public Form build() {
        return requestAsForm;
    }

    public Map<String, String> asMap() {
        return requestAsForm;
    }

    public SlackCommandRequest asRequest() {
        return new SlackRequestReader().readFrom(requestAsForm);
    }

}
