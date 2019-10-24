package com.dedale.slack;

import com.dedale.slack.server.command.SlackCommandHandler;
import com.dedale.slack.server.event.SlackEventHandler;
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;

public class MicronautSlackServer {

    public static void main(String[] args) {
        ApplicationContext applicationContext = Micronaut.run(MicronautSlackServer.class);
        applicationContext.registerSingleton(SlackCommandHandler.class, SlackCommandHandler.instance);
        applicationContext.registerSingleton(SlackEventHandler.class, SlackEventHandler.instance);
    }

}