package com.dedale.utils.jersey;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ApplicationHandler;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import com.dedale.DeDaleResourceConfig;
import com.dedale.utils.jersey.configuration.BinderConfiguration;
import com.dedale.utils.jersey.configuration.ConfigurationBuilder;

public class ApplicationInjectionRule implements MethodRule {

    private ConfigurationBuilder<ResourceConfig> configurationBuilder;

    public static ApplicationInjectionRule dedaleRule() {
        return app(new DeDaleResourceConfig());
    }

    public static ApplicationInjectionRule app(ResourceConfig configuration) {
        return new ApplicationInjectionRule(configuration);
    }

    private ApplicationInjectionRule(ResourceConfig configuration) {
        this.configurationBuilder = new ConfigurationBuilder<>(configuration);
    }

    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
        return new InjectionStatement(base, getServiceLocator(), target);
    }

    private ServiceLocator getServiceLocator() {
        ApplicationHandler applicationHandler = new ApplicationHandler(configurationBuilder.build());
        return applicationHandler.getServiceLocator();
    }

    public <B extends AbstractBinder> ApplicationInjectionRule addBinder(B binder) {
        configurationBuilder.addBinder(binder);
        return this;
    }

    public ApplicationInjectionRule configureBinding(BinderConfiguration configuration) {
        configurationBuilder.configureBinding(configuration);
        return this;
    }

}
