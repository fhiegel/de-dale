package com.dedale.slack.client.request;

import com.dedale.utils.JsonUtils;

public class SlackRequest {

	static final String TOKEN_PARAM = "token";
	static final String TEAM_ID_PARAM = "team_id";
	static final String TEAM_DOMAIN_PARAM = "team_domain";
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
        return getClass().getName() + " " + JsonUtils.asJson(this);
    }

}
