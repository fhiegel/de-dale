package com.dedale.slack.command.request;

import java.util.Map;

public class SlackRequestReader {

    private final SlackParameters parameters;

    public SlackRequestReader() {
        parameters = new SlackParameters()
                .doNothingFor(SlackCommandRequest.TOKEN_PARAM)
                .whenNamed(SlackCommandRequest.TEAM_ID_PARAM, SlackCommandRequest::setTeamId)
                .whenNamed(SlackCommandRequest.TEAM_DOMAIN_PARAM, SlackCommandRequest::setTeamDomain)
                .whenNamed(SlackCommandRequest.CHANNEL_ID_PARAM, SlackCommandRequest::setChannelId)
                .whenNamed(SlackCommandRequest.CHANNEL_NAME_PARAM, SlackCommandRequest::setChannelName)
                .whenNamed(SlackCommandRequest.USER_ID_PARAM, SlackCommandRequest::setUserId)
                .whenNamed(SlackCommandRequest.USER_NAME_PARAM, SlackCommandRequest::setUserName)
                .whenNamed(SlackCommandRequest.COMMAND_PARAM, SlackCommandRequest::setCommand)
                .whenNamed(SlackCommandRequest.TEXT_PARAM, SlackCommandRequest::setText)
                .whenNamed(SlackCommandRequest.RESPONSE_URL_PARAM, SlackCommandRequest::setResponseUrl);
    }

    public SlackCommandRequest readFrom(Map<String, String> encodedParameters) {
        SlackCommandRequest slackRequest = new SlackCommandRequest();
        encodedParameters.keySet()
                .stream()
                .map(parameters::findByName)
                .forEach(parameter -> parameter.inject(encodedParameters, slackRequest));
        return slackRequest;
    }

}
