package com.dedale;

import org.glassfish.jersey.server.ResourceConfig;

public class DeDaleResourceConfig extends ResourceConfig {
    
    public DeDaleResourceConfig() {
        packages("com.dedale.api");
    }
    
}
