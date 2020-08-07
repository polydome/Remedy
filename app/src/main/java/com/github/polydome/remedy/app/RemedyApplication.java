package com.github.polydome.remedy.app;

import com.github.polydome.remedy.api.service.ProductUpdater;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.inject.Named;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.github.polydome.remedy.api.repository")
@ComponentScan(basePackages = {"com.github.polydome.remedy.api", "com.github.polydome.remedy.csioz"})
@EnableScheduling
public class RemedyApplication extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "remedy";
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.github.polydome.remedy.api.repository");
    }

    @Bean
    @Named("registryUrl")
    public String registryUrl(@Value("${productDataSource.registryUrl}") String registryUrl) {
        return registryUrl;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RemedyApplication.class, args);
        if (Arrays.asList(args).contains("--updateProducts")) {
            ProductUpdater productUpdater = context.getBean(ProductUpdater.class);
            productUpdater.updateProducts();
        }
    }

}
