package com.dedale.slack.client.request;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.stream.Stream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;

import com.dedale.utils.jersey.MessageBodyReaderWriterUtils;

public class SlackRequestReader implements MessageBodyReader<SlackRequest> {

    SlackParameters parameters;

    public SlackRequestReader() {
        parameters = new SlackParameters()
                .doNothingFor(SlackRequest.TOKEN_PARAM)
                .whenNamed(SlackRequest.TEAM_ID_PARAM, (SlackRequest request, String value) -> request.setTeamId(value))
                .whenNamed(SlackRequest.TEAM_DOMAIN_PARAM, (request, value) -> request.setTeamDomain(value))
                .whenNamed(SlackRequest.CHANNEL_ID_PARAM, (request, value) -> request.setChannelId(value))
                .whenNamed(SlackRequest.CHANNEL_NAME_PARAM, (request, value) -> request.setChannelName(value))
                .whenNamed(SlackRequest.USER_ID_PARAM, (request, value) -> request.setUserId(value))
                .whenNamed(SlackRequest.USER_NAME_PARAM, (request, value) -> request.setUserName(value))
                .whenNamed(SlackRequest.COMMAND_PARAM, (request, value) -> request.setCommand(value))
                .whenNamed(SlackRequest.TEXT_PARAM, (request, value) -> request.setText(value))
                .whenNamed(SlackRequest.RESPONSE_URL_PARAM, (request, value) -> request.setResponseUrl(value));
    }

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type == SlackRequest.class;
    }

    @Override
    public SlackRequest readFrom(Class<SlackRequest> type, Type genericType, Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {

        SlackValuedParameters parameters = extractParameters(mediaType, entityStream);
        return createRequest(parameters);
    }

    private SlackRequest createRequest(SlackValuedParameters parameters) {
        SlackRequest slackRequest = createRequest();
        return injectParametersIntoRequest(parameters, slackRequest);
    }

    private SlackRequest createRequest() {
        return new SlackRequest();
    }

    private SlackRequest injectParametersIntoRequest(SlackValuedParameters parameters, SlackRequest slackRequest) {
        Stream<SlackRequestInjector> injectors = parameters.asStream().map(SlackValuedParameter::injectParameter);
        injectors.forEach(injector -> injector.inject(slackRequest));
        return slackRequest;
    }

    private SlackValuedParameters extractParameters(MediaType mediaType, InputStream inputStream) throws IOException {
        return new SlackValuedParameters(parameters, MessageBodyReaderWriterUtils.extractEncodedParameters(mediaType, inputStream));
    }

}
