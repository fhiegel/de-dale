package com.dedale.slack.request;

import java.util.List;
import java.util.Map;

class SlackParameter<T> {

    private String name;
    private SlackRequestInjectValue<T> injector;
    private SlackParameterExtractor<T> extractor;

    public SlackParameter(String name, SlackRequestInjectValue<T> injector, SlackParameterExtractor<T> extractor) {
        this.name = name;
        this.injector = injector;
        this.extractor = extractor;
    }

    public String name() {
        return name;
    }

    public SlackValuedParameter<T> parse(Map<String, String> parameters) {
        T value = extractor.extract(parameters);
        return new SlackValuedParameter<>(name, injector, value);
    }
}
