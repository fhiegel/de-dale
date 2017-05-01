package com.dedale.slack.client.request;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;

import com.dedale.utils.jersey.MessageBodyReaderWriterUtils;

public class SlackRequestReader implements MessageBodyReader<SlackRequest> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type == SlackRequest.class;
	}

	@Override
	public SlackRequest readFrom(Class<SlackRequest> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		
		SlackRequest slackRequest = createRequest();
		return fillRequestParameters(slackRequest, mediaType, entityStream);
	}

	private SlackRequest createRequest() {
		return new SlackRequest();
	}

	private SlackRequest fillRequestParameters(SlackRequest slackRequest, MediaType mediaType, InputStream entityStream)
			throws IOException {
		MultivaluedMap<String, String> parameters = extractParameters(mediaType, entityStream);
		for (String key : parameters.keySet()) {
			SlackRequestParameterInjector.fromParameter(key).inject(slackRequest, parameters.getFirst(key));
		}
		return slackRequest;
	}

	private MultivaluedMap<String, String> extractParameters(MediaType mediaType, InputStream inputStream)
			throws IOException {
		return MessageBodyReaderWriterUtils.extractEncodedParameters(mediaType, inputStream);
	}

}
