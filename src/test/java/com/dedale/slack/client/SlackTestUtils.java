package com.dedale.slack.client;

import javax.ws.rs.core.Form;

import com.dedale.slack.client.request.SlackRequestTestFactory;

public class SlackTestUtils {

	public static Form defaultSlackRequest(){
		return SlackRequestTestFactory.defaultSlackRequest();
	}

}
