package com.dedale.slack.server.command;

import java.util.Objects;

public class SlackCommand {

    private String text;
    private String teamId;
    private String teamDomain;
    private String channelId;
    private String channelName;
    private String userId;
    private String userName;
    private String command;
    private String responseUrl;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamDomain() {
        return teamDomain;
    }

    public void setTeamDomain(String teamDomain) {
        this.teamDomain = teamDomain;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getResponseUrl() {
        return responseUrl;
    }

    public void setResponseUrl(String responseUrl) {
        this.responseUrl = responseUrl;
    }

    // Object implementation methods

    @Override
    public String toString() {
        return "SlackRequest{" +
                "text='" + text + '\'' +
                ", teamId='" + teamId + '\'' +
                ", teamDomain='" + teamDomain + '\'' +
                ", channelId='" + channelId + '\'' +
                ", channelName='" + channelName + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", command='" + command + '\'' +
                ", responseUrl='" + responseUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SlackCommand that = (SlackCommand) o;
        return Objects.equals(text, that.text) &&
                Objects.equals(teamId, that.teamId) &&
                Objects.equals(teamDomain, that.teamDomain) &&
                Objects.equals(channelId, that.channelId) &&
                Objects.equals(channelName, that.channelName) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(command, that.command) &&
                Objects.equals(responseUrl, that.responseUrl)
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, teamId, teamDomain, channelId, channelName, userId, userName, command, responseUrl);
    }
}
