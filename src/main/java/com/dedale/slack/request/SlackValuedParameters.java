package com.dedale.slack.request;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SlackValuedParameters {

    private final List<SlackValuedParameter<?>> parameters;

    public SlackValuedParameters(SlackParameters parameters, Map<String, String> values) {
        this.parameters = parse(parameters, values);
    }

    private List<SlackValuedParameter<?>> parse(SlackParameters parameters, Map<String,String> values) {
        return values.keySet()
                .stream()
                .map(parameters::findByName)
                .map(parameter -> parameter.parse(values))
                .collect(Collectors.toList());
    }

    public Stream<SlackValuedParameter<?>> asStream() {
        return parameters.stream();
    }

}
