package com.dedale.slack;

import javax.inject.Inject;

import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;

import com.dedale.slack.client.SlackClient;

public class SlackLifecycleListener implements ContainerLifecycleListener {

    private SlackClient slack;

    @Inject
    public SlackLifecycleListener(SlackClient slack) {
        this.slack = slack;
    }

    @Override
    public void onStartup(Container container) {
        System.out.println("###");
        System.out.println("### STARTED");
        System.out.println("###");

        slack.chat().postMessage("DeDale Started").send();
    }

    @Override
    public void onReload(Container container) {
    }

    @Override
    public void onShutdown(Container container) {
        slack.technicalChat().postMessage("DeDale Stopped").send();
    }

}
