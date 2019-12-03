package com.dedale.slack.server.command;

public class SlackCommandBuilder {

    private final SlackCommand command = new SlackCommand();

    SlackCommandBuilder withTeamId(String teamId) {
        command.setTeamId(teamId);
        return this;
    }

    SlackCommandBuilder withTeamDomain(String teamDomain) {
        command.setTeamDomain(teamDomain);
        return this;
    }

    SlackCommandBuilder withChannelId(String channelId) {
        command.setChannelId(channelId);
        return this;
    }

    SlackCommandBuilder withChannelName(String channelName) {
        command.setChannelName(channelName);
        return this;
    }

    SlackCommandBuilder withUserId(String userId) {
        command.setUserId(userId);
        return this;
    }

    SlackCommandBuilder withUserName(String userName) {
        command.setUserName(userName);
        return this;
    }

    SlackCommandBuilder withCommand(String command) {
        this.command.setCommand(command);
        return this;
    }

    SlackCommandBuilder withResponseUrl(String responseUrl) {
        command.setResponseUrl(responseUrl);
        return this;
    }

    public SlackCommandBuilder withText(String text) {
        command.setText(text);
        return this;
    }

    SlackCommandBuilder withToken(String token) {
        return this;
    }

    public SlackCommand asRequest() {
        return command;
    }

}
