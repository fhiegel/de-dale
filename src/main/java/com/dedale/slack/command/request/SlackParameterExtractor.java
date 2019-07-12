package com.dedale.slack.command.request;

import java.util.Map;

@FunctionalInterface
interface SlackParameterExtractor<T> {
    T extract(Map<String, String> parameters);
}
