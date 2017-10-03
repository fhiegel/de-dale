package com.dedale.slack.client.request;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

class SlackRequestParameterInjector {
	private static final Logger log = Logger.getLogger(SlackRequestParameterInjector.class.getName());

	static final SlackRequestParameterInjector TOKEN = new SlackRequestParameterInjector(SlackRequest.TOKEN_PARAM,
			(request, parameterValue) -> {
			});

	static final SlackRequestParameterInjector TEAM_ID = new SlackRequestParameterInjector(SlackRequest.TEAM_ID_PARAM,
			(request, parameterValue) -> request.setTeamId(parameterValue));

	static final SlackRequestParameterInjector TEAM_DOMAIN = new SlackRequestParameterInjector(
			SlackRequest.TEAM_DOMAIN_PARAM, (request, parameterValue) -> request.setTeamDomain(parameterValue));

	static final SlackRequestParameterInjector CHANNEL_ID = new SlackRequestParameterInjector(
			SlackRequest.CHANNEL_ID_PARAM, (request, parameterValue) -> request.setChannelId(parameterValue));

	static final SlackRequestParameterInjector CHANNEL_NAME = new SlackRequestParameterInjector(
			SlackRequest.CHANNEL_NAME_PARAM, (request, parameterValue) -> request.setChannelName(parameterValue));

	static final SlackRequestParameterInjector USER_ID = new SlackRequestParameterInjector(SlackRequest.USER_ID_PARAM,
			(request, parameterValue) -> request.setUserId(parameterValue));

	static final SlackRequestParameterInjector USER_NAME = new SlackRequestParameterInjector(
			SlackRequest.USER_NAME_PARAM, (request, parameterValue) -> request.setUserName(parameterValue));

	static final SlackRequestParameterInjector COMMAND = new SlackRequestParameterInjector(SlackRequest.COMMAND_PARAM,
			(request, parameterValue) -> request.setCommand(parameterValue));

	static final SlackRequestParameterInjector TEXT = new SlackRequestParameterInjector(SlackRequest.TEXT_PARAM,
			(request, parameterValue) -> request.setText(parameterValue));

	static final SlackRequestParameterInjector RESPONSE_URL = new SlackRequestParameterInjector(
			SlackRequest.RESPONSE_URL_PARAM, (request, parameterValue) -> request.setResponseUrl(parameterValue));

	private static Collection<SlackRequestParameterInjector> values() {
		return Arrays.asList(TOKEN, TEAM_ID, TEAM_DOMAIN, CHANNEL_ID, CHANNEL_NAME, USER_ID, USER_NAME, COMMAND, TEXT,
				RESPONSE_URL);
	}

	protected String parameterName;
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
		return new UnknownSlackRequestParameterInjector(parameterName);
	}

	private static class UnknownSlackRequestParameterInjector extends SlackRequestParameterInjector {

		public UnknownSlackRequestParameterInjector(String parameterName) {
			super(parameterName, (l, r) -> {
			});
		}

		@Override
		public void inject(SlackRequest request, String parameterValue) {
			log.fine("Unknown parameter : name=" + parameterName + ", value=" + parameterValue);
			log.info("Unknown parameter : name=" + parameterName + ", value=" + parameterValue);
			log.warning("Unknown parameter : name=" + parameterName + ", value=" + parameterValue);
			log.severe("Unknown parameter : name=" + parameterName + ", value=" + parameterValue);
		}

	}

}