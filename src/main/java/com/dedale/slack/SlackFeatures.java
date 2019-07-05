package com.dedale.slack;

import com.dedale.slack.request.SlackRequest;
import com.dedale.slack.request.SlackRequestReader;
import io.micronaut.context.annotation.Factory;
import io.micronaut.core.convert.TypeConverter;

import javax.inject.Singleton;
import java.util.Map;
import java.util.Optional;

@Factory
public class SlackFeatures {

    @Singleton
    public TypeConverter<Map<String, String>, SlackRequest> slackRequestConverter() {
        SlackRequestReader reader = new SlackRequestReader();
        return (parameters, targetType, context) -> Optional.of(reader.readFrom(parameters));
    }

}
