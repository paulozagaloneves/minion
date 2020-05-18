package com.example.k8s.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "minion.account")
public class MinionConfig {

    private String type = "generic-minion";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
