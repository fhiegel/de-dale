package com.dedale.slack.request;

@FunctionalInterface
interface SlackRequestInjectValue<T> {
    void inject(SlackRequest request, T value);
}