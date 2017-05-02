package com.dedale.utils.jersey;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.glassfish.jersey.internal.util.collection.NullableMultivaluedHashMap;
import org.glassfish.jersey.message.internal.AbstractFormProvider;

public class MessageBodyReaderWriterUtils {

	private static AbstractFormProvider<Void> jerseyFormUtil = new AbstractFormProvider<Void>() {

		@Override
		public void writeTo(Void t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
				MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
				throws IOException, WebApplicationException {
		}

		@Override
		public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
			return false;
		}

		@Override
		public Void readFrom(Class<Void> type, Type genericType, Annotation[] annotations, MediaType mediaType,
				MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
				throws IOException, WebApplicationException {
			return null;
		}

		@Override
		public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
			return false;
		}
	};

	public static MultivaluedMap<String, String> extractEncodedParameters(MediaType mediaType, InputStream inputStream)
			throws IOException {
		return jerseyFormUtil.readFrom(new NullableMultivaluedHashMap<>(), mediaType, true, inputStream);
	}

}
