package com.dedale.slack.command.request;

import java.util.*;
import java.util.logging.Logger;

class SlackParameters {

    private static final Logger log = Logger.getLogger(SlackParameters.class.getName());

    private static final SlackParameterExtractor<Void> NO_EXTRACT = (parameter) -> null;
    private static final SlackRequestInjector<Void> NO_INJECT = (request, parameter) -> {};

    private final Collection<SlackParameter<?>> parameters = new ArrayList<>();

    SlackParameters doNothingFor(String name) {
        return addParameter(name, NO_INJECT, NO_EXTRACT);
    }

    SlackParameters whenNamed(String name, SlackRequestInjector<String> injector) {
        return addParameter(name, injector, (parameters) -> parameters.getOrDefault(name, ""));
    }

    private <T> SlackParameters addParameter(String name, SlackRequestInjector<T> injector, SlackParameterExtractor<T> extractor) {
        return addParameter(new SlackParameter<>(name, injector, extractor));
    }

    private SlackParameters addParameter(SlackParameter<?> parameter) {
        parameters.add(parameter);
        return this;
    }

    SlackParameter<?> findByName(String name) {
        return parameters.stream()
                .filter(parameter -> parameter.name().equals(name))
                .findFirst()
                .orElse(logParameter(name));
    }

    private SlackParameter<String> logParameter(String name) {
        return new SlackParameter<>(name, injectIntoLog(name), parameters -> parameters.getOrDefault(name, ""));
    }

    private SlackRequestInjector<String> injectIntoLog(String name) {
        return (request, parameter) -> log.warning(() -> String.format("Unknown parameter : name=%s, value=%s", name, parameter));
    }

}