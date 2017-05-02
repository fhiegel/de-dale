package com.dedale.slack.client.request;

@FunctionalInterface
interface SlackRequestInjector {
	void inject(SlackRequest request, String parameterValue);
}