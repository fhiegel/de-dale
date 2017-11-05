package com.dedale.slack.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;

import org.junit.Test;

import com.dedale.slack.request.SlackRequest;
import com.dedale.slack.request.SlackRequestReader;
import com.dedale.utils.FileTestUtils;

public class SlackRequestReaderTest {

	private SlackRequestReader reader = new SlackRequestReader();

	@Test
	public void should_parse_channelId_from_request() throws Exception {
		// Given
		InputStream inputStream = getInputStreamFromFile("default_request");

		// When
		SlackRequest slackRequest = readFrom(inputStream);

		// Then
		assertThat(slackRequest.getChannelId()).isEqualTo("C2147483705");
	}

	@Test
	public void should_parse_channelName_from_request() throws Exception {
		// Given
		InputStream inputStream = getInputStreamFromFile("default_request");

		// When
		SlackRequest slackRequest = readFrom(inputStream);

		// Then
		assertThat(slackRequest.getChannelName()).isEqualTo("test");
	}

	@Test
	public void should_parse_command_from_request() throws Exception {
		// Given
		InputStream inputStream = getInputStreamFromFile("default_request");

		// When
		SlackRequest slackRequest = readFrom(inputStream);

		// Then
		assertThat(slackRequest.getCommand()).isEqualTo("/weather");
	}

	@Test
	public void should_parse_responseUrl_from_request() throws Exception {
		// Given
		InputStream inputStream = getInputStreamFromFile("default_request");

		// When
		SlackRequest slackRequest = readFrom(inputStream);

		// Then
		assertThat(slackRequest.getResponseUrl()).isEqualTo("https://hooks.slack.com/commands/1234/5678");
	}

	@Test
	public void should_parse_teamDomain_from_request() throws Exception {
		// Given
		InputStream inputStream = getInputStreamFromFile("default_request");

		// When
		SlackRequest slackRequest = readFrom(inputStream);

		// Then
		assertThat(slackRequest.getTeamDomain()).isEqualTo("example");
	}

	@Test
	public void should_parse_teamId_from_request() throws Exception {
		// Given
		InputStream inputStream = getInputStreamFromFile("default_request");

		// When
		SlackRequest slackRequest = readFrom(inputStream);

		// Then
		assertThat(slackRequest.getTeamId()).isEqualTo("T0001");
	}

	@Test
	public void should_parse_text_from_request() throws Exception {
		// Given
		InputStream inputStream = getInputStreamFromFile("default_request");

		// When
		SlackRequest slackRequest = readFrom(inputStream);

		// Then
		assertThat(slackRequest.getText()).isEqualTo("94070");
	}

	@Test
	public void should_parse_userId_from_request() throws Exception {
		// Given
		InputStream inputStream = getInputStreamFromFile("default_request");

		// When
		SlackRequest slackRequest = readFrom(inputStream);

		// Then
		assertThat(slackRequest.getUserId()).isEqualTo("U2147483697");
	}

	@Test
	public void should_parse_userName_from_request() throws Exception {
		// Given
		InputStream inputStream = getInputStreamFromFile("default_request");

		// When
		SlackRequest slackRequest = readFrom(inputStream);

		// Then
		assertThat(slackRequest.getUserName()).isEqualTo("Steve");
	}

	//
	// Utilitaires
	//

	private InputStream getInputStreamFromFile(String filePath) throws FileNotFoundException, UnsupportedEncodingException {
		String inputAsString = FileTestUtils.getResourceFileAsString(getClass(), filePath);
		inputAsString = inputAsString.replaceAll(System.lineSeparator(), "&");
		return new ByteArrayInputStream(inputAsString.getBytes());
	}

	private SlackRequest readFrom(InputStream inputStream) throws IOException {
		return reader.readFrom(SlackRequest.class, null, null, MediaType.APPLICATION_FORM_URLENCODED_TYPE,
				new MultivaluedHashMap<>(), inputStream);
	}

}
