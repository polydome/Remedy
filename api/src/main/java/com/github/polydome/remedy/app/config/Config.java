package com.github.polydome.remedy.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackages = "com.github.polydome.remedy.repository")
@ComponentScan("com.github.polydome.remedy.repository")
public class Config extends AbstractMongoClientConfiguration {
    @Override
    protected String getDatabaseName() {
        return "remedy";
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.github.polydome.remedy.repository");
    }
}
