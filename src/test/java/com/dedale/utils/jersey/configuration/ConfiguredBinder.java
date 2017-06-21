package com.dedale.utils.jersey.configuration;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

class ConfiguredBinder extends AbstractBinder {
    
    private BinderConfiguration configuration;
    
    public ConfiguredBinder(BinderConfiguration configuration) {
        this.configuration = configuration;
    }
    
    @Override
    protected void configure() {
        configuration.configure(this);
    }
}