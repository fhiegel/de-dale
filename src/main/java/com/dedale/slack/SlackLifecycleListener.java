package com.dedale.slack;

import com.dedale.slack.client.SlackClient;

import javax.inject.Inject;

public class SlackLifecycleListener/* implements ContainerLifecycleListener */{

    private SlackClient slack;

    @Inject
    public SlackLifecycleListener(SlackClient slack) {
        this.slack = slack;
    }

//    public void onStartup(Container container) {
//        System.out.println("###");
//        System.out.println("### STARTED");
//        System.out.println("###");
//
//        slack.technicalChat().postMessage("DeDale Started").send();
//    }

//    @Override
//    public void onReload(Container container) {
//    }
//
//    @Override
//    public void onShutdown(Container container) {
//        slack.technicalChat().postMessage("DeDale Stopped").send();
//    }

}
