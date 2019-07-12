package com.dedale.slack.command.request;

import java.util.Map;

class SlackParameter<T> {

    private String name;
    private SlackRequestInjector<T> injector;
    private SlackParameterExtractor<T> extractor;

    public SlackParameter(String name, SlackRequestInjector<T> injector, SlackParameterExtractor<T> extractor) {
        this.name = name;
        this.injector = injector;
        this.extractor = extractor;
    }

    public String name() {
        return name;
    }

    public void inject(Map<String, String> parameters, SlackCommandRequest request) {
        T value = extractor.extract(parameters);
        injector.inject(request, value);
    }
}
