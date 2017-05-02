package com.dedale.slack.client.request;

import javax.ws.rs.core.Form;

public class SlackRequestBuilder {

    private Form requestAsForm = new Form();

    public SlackRequestBuilder withText(String text) {
        requestAsForm.param(SlackRequest.TEXT_PARAM, text);
        return this;
    }

    public SlackRequestBuilder withTeamId(String teamId) {
        requestAsForm.param(SlackRequest.TEAM_ID_PARAM, teamId);
        return this;
    }

    public SlackRequestBuilder withTeamDomain(String teamDomain) {
        requestAsForm.param(SlackRequest.TEAM_DOMAIN_PARAM, teamDomain);
        return this;
    }

    public SlackRequestBuilder withChannelId(String channelId) {
        requestAsForm.param(SlackRequest.CHANNEL_ID_PARAM, channelId);
        return this;
    }

    public SlackRequestBuilder withChannelName(String channelName) {
        requestAsForm.param(SlackRequest.CHANNEL_NAME_PARAM, channelName);
        return this;
    }

    public SlackRequestBuilder withUserId(String userId) {
        requestAsForm.param(SlackRequest.USER_ID_PARAM, userId);
        return this;
    }

    public SlackRequestBuilder withUserName(String userName) {
        requestAsForm.param(SlackRequest.USER_NAME_PARAM, userName);
        return this;
    }

    public SlackRequestBuilder withCommand(String command) {
        requestAsForm.param(SlackRequest.COMMAND_PARAM, command);
        return this;
    }

    public SlackRequestBuilder withResponseUrl(String responseUrl) {
        requestAsForm.param(SlackRequest.RESPONSE_URL_PARAM, responseUrl);
        return this;
    }

    public SlackRequestBuilder withToken(String token) {
        requestAsForm.param(SlackRequest.TOKEN_PARAM, token);
        return this;
    }

    public Form build() {
        return requestAsForm;
    }

}
