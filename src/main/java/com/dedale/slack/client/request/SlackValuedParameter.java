package com.dedale.slack.client.request;

class SlackValuedParameter<T> {

    private final String name;
    private final T value;
    private SlackParameterRequestInjector<T> injector;

    public SlackValuedParameter(String name, SlackParameterRequestInjector<T> injector, T values) {
        this.name = name;
        this.injector = injector;
        this.value = values;
    }

    public String name() {
        return name;
    }

    public T value() {
        return value;
    }

    public SlackRequestInjector injectParameter() {
        return request -> injector.inject(request, value());
    }

    @Override
    public String toString() {
        return "SlackParameter [name=" + name + ", values=" + value + "]";
    }
}
