package com.dedale.slack.server.command.request;

import com.dedale.slack.server.command.SlackCommand;

public class SlackCommandRequestBuilder {

    private final SlackCommand request = new SlackCommand();

    public SlackCommandRequestBuilder withTeamId(String teamId) {
        request.setTeamId(teamId);
        return this;
    }

    public SlackCommandRequestBuilder withTeamDomain(String teamDomain) {
        request.setTeamDomain(teamDomain);
        return this;
    }

    public SlackCommandRequestBuilder withChannelId(String channelId) {
        request.setChannelId(channelId);
        return this;
    }

    public SlackCommandRequestBuilder withChannelName(String channelName) {
        request.setChannelName(channelName);
        return this;
    }

    public SlackCommandRequestBuilder withUserId(String userId) {
        request.setUserId(userId);
        return this;
    }

    public SlackCommandRequestBuilder withUserName(String userName) {
        request.setUserName(userName);
        return this;
    }

    public SlackCommandRequestBuilder withCommand(String command) {
        request.setCommand(command);
        return this;
    }

    public SlackCommandRequestBuilder withResponseUrl(String responseUrl) {
        request.setResponseUrl(responseUrl);
        return this;
    }

    public SlackCommandRequestBuilder withText(String text) {
        request.setText(text);
        return this;
    }

    public SlackCommandRequestBuilder withToken(String token) {
        return this;
    }

    public SlackCommand asRequest() {
        return request;
    }

}
