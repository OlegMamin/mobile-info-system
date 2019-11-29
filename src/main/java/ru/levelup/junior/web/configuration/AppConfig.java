package ru.levelup.junior.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 * Created by otherz on 14.11.2019.
 */
@Configuration
@ComponentScan(basePackages = "ru.levelup.junior")
@EnableJpaRepositories(basePackages = "ru.levelup.junior")
public class AppConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setPersistenceUnitName("ProdPersistenceUnit");
        return bean;
    }
}
