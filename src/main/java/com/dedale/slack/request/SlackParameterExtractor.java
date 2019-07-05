package com.dedale.slack.request;

import java.util.List;
import java.util.Map;

@FunctionalInterface
interface SlackParameterExtractor<T> {
    T extract(Map<String, String> parameters);
}
