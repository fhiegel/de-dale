package com.dedale.slack.request;

import java.util.*;
import java.util.logging.Logger;

class SlackParameters {

    private static final Logger log = Logger.getLogger(SlackParameters.class.getName());

    private static final SlackRequestInjectValue<Void> NO_INJECT = (request, parameter) -> {
    };
    private static final SlackParameterExtractor<Void> NO_EXTRACT = (parameter) -> null;

    private final Collection<SlackParameter<?>> parameters = new ArrayList<>();

    public SlackParameters doNothingFor(String name) {
        return addParameter(name, NO_INJECT, NO_EXTRACT);
    }

    public SlackParameters whenNamed(String name, SlackRequestInjectValue<String> injector) {
        return addParameter(name, injector, (parameters) -> extractValueByDefault(name, parameters));
    }

    private String extractValueByDefault(String name, Map<String, String> parameters) {
        return parameters.getOrDefault(name, "");
    }

    private <T> SlackParameters addParameter(String name, SlackRequestInjectValue<T> injector, SlackParameterExtractor<T> extractor) {
        return addParameter(new SlackParameter<>(name, injector, extractor));
    }

    private SlackParameters addParameter(SlackParameter<?> parameter) {
        parameters.add(parameter);
        return this;
    }

    public SlackParameter<?> findByName(String name) {
        return parameters.stream()
                .filter(parameter -> parameter.name().equals(name))
                .findFirst()
                .orElse(logParameter(name));
    }

    private SlackParameter<String> logParameter(String name) {
        return new SlackParameter<>(name, injectIntoLog(name), parameters -> extractValueByDefault(name, parameters));
    }

    private SlackRequestInjectValue<String> injectIntoLog(String name) {
        return (request, parameter) -> log.warning(() -> String.format("Unknown parameter : name=%s, value=%s", name, parameter));
    }

}