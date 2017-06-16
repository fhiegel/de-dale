package com.dedale.utils.jersey;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ApplicationHandler;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import com.dedale.DeDaleResourceConfig;

public class ApplicationInjectionRule implements MethodRule {
    
    private ResourceConfigBuilder configurable;
    
    public static ApplicationInjectionRule rule() {
        return app(new DeDaleResourceConfig());
    }
    
    public static ApplicationInjectionRule app(ResourceConfig application) {
        return new ApplicationInjectionRule(application);
    }
    
    private ApplicationInjectionRule(ResourceConfig application) {
        this.configurable = new ResourceConfigBuilder(application);
    }
    
    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
        injectInto(target);
        return base;
    }
    
    private void injectInto(Object target) {
        ApplicationHandler applicationHandler = new ApplicationHandler(configurable.build());
        ServiceLocator serviceLocator = applicationHandler.getServiceLocator();
        serviceLocator.inject(target);
    }
    
    public <B extends AbstractBinder> ApplicationInjectionRule addBinder(B binder) {
        configurable.addBinder(binder);
        return this;
    }
    
    public ApplicationInjectionRule configureBinding(BinderConfiguration configuration) {
        configurable.configureBinding(configuration);
        return this;
    }
    
}
