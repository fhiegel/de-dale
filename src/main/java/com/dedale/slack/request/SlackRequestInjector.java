package com.dedale.slack.request;

@FunctionalInterface
interface SlackRequestInjector {
    void inject(SlackRequest request);
}