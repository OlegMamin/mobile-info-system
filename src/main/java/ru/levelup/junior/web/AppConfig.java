package ru.levelup.junior.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by otherz on 14.11.2019.
 */
@Configuration
@ComponentScan(basePackages = "ru.levelup.junior")
public class AppConfig {
    @Bean
    public EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("TestPersistenceUnit");
    }

    @Bean
    public EntityManager getEntityManager(EntityManagerFactory factory) {
        return factory.createEntityManager();
    }
}
