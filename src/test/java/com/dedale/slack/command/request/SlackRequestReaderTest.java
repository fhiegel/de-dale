package com.dedale.slack.command.request;

import com.dedale.utils.resources.Resources;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class SlackRequestReaderTest {

    private SlackRequestReader reader = new SlackRequestReader();

    @Test
    void should_parse_channelId_from_request() throws Exception {
        // Given
        Map<String, String> inputStream = getInputStreamFromFile("default_request");

        // When
        SlackCommandRequest slackRequest = readFrom(inputStream);

        // Then
        assertThat(slackRequest.getChannelId()).isEqualTo("C2147483705");
    }

    @Test
    void should_parse_channelName_from_request() throws Exception {
        // Given
        Map<String, String> inputStream = getInputStreamFromFile("default_request");

        // When
        SlackCommandRequest slackRequest = readFrom(inputStream);

        // Then
        assertThat(slackRequest.getChannelName()).isEqualTo("test");
    }

    @Test
    void should_parse_command_from_request() throws Exception {
        // Given
        Map<String, String> inputStream = getInputStreamFromFile("default_request");

        // When
        SlackCommandRequest slackRequest = readFrom(inputStream);

        // Then
        assertThat(slackRequest.getCommand()).isEqualTo("/weather");
    }

    @Test
    void should_parse_responseUrl_from_request() throws Exception {
        // Given
        Map<String, String> inputStream = getInputStreamFromFile("default_request");

        // When
        SlackCommandRequest slackRequest = readFrom(inputStream);

        // Then
        assertThat(slackRequest.getResponseUrl()).isEqualTo("https://hooks.slack.com/commands/1234/5678");
    }

    @Test
    void should_parse_teamDomain_from_request() throws Exception {
        // Given
        Map<String, String> inputStream = getInputStreamFromFile("default_request");

        // When
        SlackCommandRequest slackRequest = readFrom(inputStream);

        // Then
        assertThat(slackRequest.getTeamDomain()).isEqualTo("example");
    }

    @Test
    void should_parse_teamId_from_request() throws Exception {
        // Given
        Map<String, String> inputStream = getInputStreamFromFile("default_request");

        // When
        SlackCommandRequest slackRequest = readFrom(inputStream);

        // Then
        assertThat(slackRequest.getTeamId()).isEqualTo("T0001");
    }

    @Test
    void should_parse_text_from_request() throws Exception {
        // Given
        Map<String, String> inputStream = getInputStreamFromFile("default_request");

        // When
        SlackCommandRequest slackRequest = readFrom(inputStream);

        // Then
        assertThat(slackRequest.getText()).isEqualTo("94070");
    }

    @Test
    void should_parse_userId_from_request() throws Exception {
        // Given
        Map<String, String> inputStream = getInputStreamFromFile("default_request");

        // When
        SlackCommandRequest slackRequest = readFrom(inputStream);

        // Then
        assertThat(slackRequest.getUserId()).isEqualTo("U2147483697");
    }

    @Test
    void should_parse_userName_from_request() throws Exception {
        // Given
        Map<String, String> inputStream = getInputStreamFromFile("default_request");

        // When
        SlackCommandRequest slackRequest = readFrom(inputStream);

        // Then
        assertThat(slackRequest.getUserName()).isEqualTo("Steve");
    }

    //
    // Utilitaires
    //

    private Map<String, String> getInputStreamFromFile(String filePath) throws IOException {
        Path path = Resources.getRelativeTo(getClass(), filePath).asPath();
        return Files.lines(path)
                .filter(s -> s.matches("^\\w+=\\S+"))
                .collect(Collectors.toMap(k -> k.split("=")[0],
                        v -> v.split("=")[1]));
    }

    private SlackCommandRequest readFrom(Map<String, String> inputStream) {
        return reader.readFrom(inputStream);
    }

}
