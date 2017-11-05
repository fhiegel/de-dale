package com.dedale.slack.request;

class SlackValuedParameter<T> {

    private final String name;
    private final T value;
    private SlackRequestInjectValue<T> injector;

    public SlackValuedParameter(String name, SlackRequestInjectValue<T> injector, T value) {
        this.name = name;
        this.injector = injector;
        this.value = value;
    }

    public SlackRequestInjector injectParameter() {
        return request -> injector.inject(request, value);
    }

    @Override
    public String toString() {
        return "SlackParameter [name=" + name + ", values=" + value + "]";
    }
}
