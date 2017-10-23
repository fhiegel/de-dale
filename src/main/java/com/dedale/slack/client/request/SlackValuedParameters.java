package com.dedale.slack.client.request;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.core.MultivaluedMap;

class SlackValuedParameters {

    private List<SlackValuedParameter<?>> parameters;

    public SlackValuedParameters(SlackParameters parameters, MultivaluedMap<String, String> values) {
        this.parameters = parse(parameters, values);
    }

    private List<SlackValuedParameter<?>> parse(SlackParameters parameters, MultivaluedMap<String, String> values) {
        return values.keySet()
                .stream()
                .map(name -> parameters.findByName(name))
                .map(parameter -> parameter.parse(values))
                .collect(Collectors.toList());
    }

    public Stream<SlackValuedParameter<?>> asStream() {
        return parameters.stream();
    }

}
