package com.dedale.utils.jersey;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ApplicationHandler;
import org.glassfish.jersey.test.spi.TestContainer;

import com.dedale.utils.Whitebox;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class TestCycleWrapper {

    private TestContainer testContainer;
    private Client client;
    private ApplicationHandler applicationHandler;
    private ServiceLocator serviceLocator;

    public TestCycleWrapper(TestContainer testContainer) {
        this.testContainer = testContainer;
    }

    public ServiceLocator getServiceLocator() {
        if (serviceLocator == null) {
            serviceLocator = getApplicationHandler().getServiceLocator();
        }
        return serviceLocator;
    }

    public Client getClient() {
        if (client == null) {
            client = ClientBuilder.newClient(testContainer.getClientConfig());
        }
        return client;
    }

    public ApplicationHandler getApplicationHandler() {
        if (applicationHandler == null) {
            applicationHandler = (ApplicationHandler) Whitebox.getInternalState(testContainer, "appHandler");
        }
        return applicationHandler;
    }

    public void release() {
        safeClose(client);
    }

    public void safeClose(final Client... clients) {
        if (clients == null || clients.length == 0) {
            return;
        }

        for (final Client c : clients) {
            if (c == null) {
                continue;
            }
            try {
                c.close();
            } catch (final Throwable t) {
                log.warn("Error closing a client instance.", t);
            }
        }
    }

}