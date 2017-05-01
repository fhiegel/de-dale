package com.dedale.slack.client.request;

enum SlackRequestParameterInjector {
	TOKEN(SlackRequest.TOKEN_PARAM, (request, parameterValue) -> {
	}),

	TEAM_ID(SlackRequest.TEAM_ID_PARAM, (request, parameterValue) -> request.setTeamId(parameterValue)),

	TEAM_DOMAIN(SlackRequest.TEAM_DOMAIN_PARAM, (request, parameterValue) -> request.setTeamDomain(parameterValue)),

	CHANNEL_ID(SlackRequest.CHANNEL_ID_PARAM, (request, parameterValue) -> request.setChannelId(parameterValue)),

	CHANNEL_NAME(SlackRequest.CHANNEL_NAME_PARAM, (request, parameterValue) -> request.setChannelName(parameterValue)),

	USER_ID(SlackRequest.USER_ID_PARAM, (request, parameterValue) -> request.setUserId(parameterValue)),

	USER_NAME(SlackRequest.USER_NAME_PARAM, (request, parameterValue) -> request.setUserName(parameterValue)),

	COMMAND(SlackRequest.COMMAND_PARAM, (request, parameterValue) -> request.setCommand(parameterValue)),

	TEXT(SlackRequest.TEXT_PARAM, (request, parameterValue) -> request.setText(parameterValue)),

	RESPONSE_URL(SlackRequest.RESPONSE_URL_PARAM, (request, parameterValue) -> request.setResponseUrl(parameterValue));

	private String parameterName;
	private SlackRequestInjector injector;

	private SlackRequestParameterInjector(String parameterName, SlackRequestInjector injector) {
		this.parameterName = parameterName;
		this.injector = injector;
	}

	public void inject(SlackRequest request, String parameterValue) {
		injector.inject(request, parameterValue);
	}

	static SlackRequestParameterInjector fromParameter(String parameterName) {
		for (SlackRequestParameterInjector injector : values()) {
			if (injector.parameterName.equals(parameterName)) {
				return injector;
			}
		}
		throw new IllegalArgumentException("Unknown parameter : " + parameterName);
	}
}