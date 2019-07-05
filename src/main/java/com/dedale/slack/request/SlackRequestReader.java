package com.dedale.slack.request;

import java.util.Map;
import java.util.stream.Stream;

public class SlackRequestReader /*implements MessageBodyReader<SlackRequest>*/ {

    private final SlackParameters parameters;

    public SlackRequestReader() {
        parameters = new SlackParameters()
                .doNothingFor(SlackRequest.TOKEN_PARAM)
                .whenNamed(SlackRequest.TEAM_ID_PARAM, SlackRequest::setTeamId)
                .whenNamed(SlackRequest.TEAM_DOMAIN_PARAM, SlackRequest::setTeamDomain)
                .whenNamed(SlackRequest.CHANNEL_ID_PARAM, SlackRequest::setChannelId)
                .whenNamed(SlackRequest.CHANNEL_NAME_PARAM, SlackRequest::setChannelName)
                .whenNamed(SlackRequest.USER_ID_PARAM, SlackRequest::setUserId)
                .whenNamed(SlackRequest.USER_NAME_PARAM, SlackRequest::setUserName)
                .whenNamed(SlackRequest.COMMAND_PARAM, SlackRequest::setCommand)
                .whenNamed(SlackRequest.TEXT_PARAM, SlackRequest::setText)
                .whenNamed(SlackRequest.RESPONSE_URL_PARAM, SlackRequest::setResponseUrl);
    }

    public SlackRequest readFrom(Map<String, String> encodedParameters) {
        SlackValuedParameters valuedParameters = new SlackValuedParameters(parameters, encodedParameters);
        return createRequest(valuedParameters);
    }

    private SlackRequest createRequest() {
        return new SlackRequest();
    }

    private SlackRequest createRequest(SlackValuedParameters parameters) {
        SlackRequest slackRequest = createRequest();
        return injectParametersIntoRequest(parameters, slackRequest);
    }

    private SlackRequest injectParametersIntoRequest(SlackValuedParameters parameters, SlackRequest slackRequest) {
        Stream<SlackRequestInjector> injectors = parameters.asStream().map(SlackValuedParameter::injectParameter);
        injectors.forEach(injector -> injector.inject(slackRequest));
        return slackRequest;
    }

}
