package com.dedale.slack.command.request;

@FunctionalInterface
interface SlackRequestInjector<T> {
    void inject(SlackCommandRequest request, T value);
}