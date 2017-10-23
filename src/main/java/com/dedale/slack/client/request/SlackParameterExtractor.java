package com.dedale.slack.client.request;

import javax.ws.rs.core.MultivaluedMap;

@FunctionalInterface
interface SlackParameterExtractor<T> {

    T extract(MultivaluedMap<String, String> parameters);
}
