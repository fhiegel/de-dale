package com.dedale.utils.jersey.configuration;

import javax.ws.rs.core.Configurable;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ConfigurationBuilder<C extends Configurable<C>> {
    
    private C configurable;
    
    public ConfigurationBuilder(C applicationConfiguration) {
        this.configurable = applicationConfiguration;
    }
    
    public <B extends AbstractBinder> ConfigurationBuilder<C> addBinder(B binder) {
        configurable.register(binder);
        return this;
    }
    
    public ConfigurationBuilder<C> configureBinding(BinderConfiguration configuration) {
        return addBinder(new ConfiguredBinder(configuration));
    }
    
    public C build() {
        return configurable;
    }
    
}
