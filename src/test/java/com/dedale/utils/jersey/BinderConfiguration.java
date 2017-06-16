package com.dedale.utils.jersey;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

@FunctionalInterface
public interface BinderConfiguration {
    void configure(AbstractBinder binder);
}