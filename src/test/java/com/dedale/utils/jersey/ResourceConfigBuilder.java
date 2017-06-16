package com.dedale.utils.jersey;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

public class ResourceConfigBuilder {
    
    private ResourceConfig applicationConfiguration;
    
    public ResourceConfigBuilder(ResourceConfig applicationConfiguration) {
        this.applicationConfiguration = applicationConfiguration;
    }
    
    public <B extends AbstractBinder> ResourceConfigBuilder addBinder(B binder) {
        applicationConfiguration.register(binder);
        return this;
    }
    
    public ResourceConfigBuilder configureBinding(BinderConfiguration configuration) {
        return addBinder(new ConfiguredBinder(configuration));
    }
    
    public ResourceConfig build() {
        return applicationConfiguration;
    }
    
}
