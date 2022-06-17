package com.example.properties;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "spring.dynamic")
public class DynamicDataSourceProperties {
    public Map<String, DataSourceProperties> datasources;

    public String defaultdatasource;

    public void setDefaultdatasource(String defaultdatasource) {
        this.defaultdatasource = defaultdatasource;
    }

    public void setDatasources(Map<String, DataSourceProperties> datasources) {
        this.datasources = datasources;
    }
}
