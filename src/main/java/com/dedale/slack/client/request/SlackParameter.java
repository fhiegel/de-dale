package com.dedale.slack.client.request;

import javax.ws.rs.core.MultivaluedMap;

class SlackParameter<T> {

    private String name;
    private SlackParameterRequestInjector<T> injector;
    private SlackParameterExtractor<T> extractor;

    public SlackParameter(String name, SlackParameterRequestInjector<T> injector, SlackParameterExtractor<T> extractor) {
        this.name = name;
        this.injector = injector;
        this.extractor = extractor;
    }

    public String name() {
        return name;
    }

    public SlackValuedParameter<T> parse(MultivaluedMap<String, String> parameters) {
        T value = extractor.extract(parameters);
        return new SlackValuedParameter<>(name, injector, value);
    }
}
