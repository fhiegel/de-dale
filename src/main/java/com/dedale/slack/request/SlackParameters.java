package com.dedale.slack.request;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.core.MultivaluedMap;

class SlackParameters {
    private static final Logger log = Logger.getLogger(SlackParameters.class.getName());

    private static final SlackRequestInjectValue<Void> NO_INJECT = (request, parameter) -> {
    };
    private static final SlackParameterExtractor<Void> NO_EXTRACT = (parameter) -> {
        return null;
    };

    private final Collection<SlackParameter<?>> parameters = new ArrayList<>();

    public SlackParameters doNothingFor(String name) {
        return addParameter(name, NO_INJECT, NO_EXTRACT);
    }

    public SlackParameters whenNamed(String name, SlackRequestInjectValue<String> injector) {
        return addParameter(name, injector, (parameters) -> extractValueByDefault(name, parameters));
    }

    private String extractValueByDefault(String name, MultivaluedMap<String, String> parameters) {
        return extractList(name, parameters).get(0);
    }

    private List<String> extractList(String name, MultivaluedMap<String, String> parameters) {
        return parameters.getOrDefault(name, Collections.emptyList());
    }

    private <T> SlackParameters addParameter(String name, SlackRequestInjectValue<T> injector, SlackParameterExtractor<T> extractor) {
        return addParameter(new SlackParameter<>(name, injector, extractor));
    }

    private SlackParameters addParameter(SlackParameter<?> parameter) {
        parameters.add(parameter);
        return this;
    }

    public SlackParameter<?> findByName(String name) {
        return parameters.stream().filter(parameter -> parameter.name().equals(name)).findFirst().orElse(logParameter(name));
    }

    private SlackParameter<List<String>> logParameter(String name) {
        return new SlackParameter<List<String>>(name, (request, parameter) -> {
            log.warning("Unknown parameter : name=" + name + ", value=" + parameter);
        }, parameters -> extractList(name, parameters));
    }

}