package com.dedale.utils.jersey;

import java.util.function.Supplier;

import org.glassfish.hk2.api.ServiceLocator;
import org.junit.runners.model.Statement;

class InjectionStatement extends StatementDecorator {

    private Object injectee;
    private Supplier<ServiceLocator> serviceLocatorProvider = this::getDefaultServiceLocator;

    public InjectionStatement(Statement statement, ServiceLocator serviceLocator, Object injectee) {
        this(statement, () -> serviceLocator, injectee);
    }

    public InjectionStatement(Statement statement, Supplier<ServiceLocator> serviceLocatorProvider, Object injectee) {
        super(statement);
        this.injectee = injectee;
        this.serviceLocatorProvider = serviceLocatorProvider;
    }

    @Override
    protected void prepareStatement() throws Throwable {
        serviceLocatorProvider.get().inject(injectee);
    }

    public InjectionStatement withInjecteee(Object injectee) {
        this.injectee = injectee;
        return this;
    }

    public InjectionStatement withServiceLocator(Supplier<ServiceLocator> serviceLocatorProvider) {
        this.serviceLocatorProvider = serviceLocatorProvider;
        return this;
    }

    private ServiceLocator getDefaultServiceLocator() {
        throw new IllegalStateException("Cannot retrieve ServiceLocator.");
    }

}