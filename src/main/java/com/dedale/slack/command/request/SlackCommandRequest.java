package com.dedale.slack.command.request;

import java.util.Objects;

public class SlackCommandRequest {

    static final String TOKEN_PARAM = "token";
    static final String TEAM_ID_PARAM = "team_id";
    static final String API_APP_ID = "api_app_id";
    static final String TEAM_DOMAIN_PARAM = "team_domain";
    static final String CHANNEL_PARAM = "channel";
    static final String CHANNEL_ID_PARAM = "channel_id";
    static final String CHANNEL_NAME_PARAM = "channel_name";
    static final String USER_ID_PARAM = "user_id";
    static final String USER_NAME_PARAM = "user_name";
    static final String COMMAND_PARAM = "command";
    static final String TEXT_PARAM = "text";
    static final String RESPONSE_URL_PARAM = "response_url";

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

    void setText(String text) {
        this.text = text;
    }

    public String getTeamId() {
        return teamId;
    }

    void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamDomain() {
        return teamDomain;
    }

    void setTeamDomain(String teamDomain) {
        this.teamDomain = teamDomain;
    }

    public String getChannelId() {
        return channelId;
    }

    void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getUserId() {
        return userId;
    }

    void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommand() {
        return command;
    }

    void setCommand(String command) {
        this.command = command;
    }

    public String getResponseUrl() {
        return responseUrl;
    }

    void setResponseUrl(String responseUrl) {
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
        SlackCommandRequest that = (SlackCommandRequest) o;
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
