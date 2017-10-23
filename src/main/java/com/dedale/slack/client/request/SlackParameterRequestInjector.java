package com.dedale.slack.client.request;

@FunctionalInterface
interface SlackParameterRequestInjector<T> {

    void inject(SlackRequest request, T value);

}