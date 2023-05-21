package com.nhnacademy.family.config;

import lombok.RequiredArgsConstructor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = "com.nhnacademy.family", excludeFilters = @ComponentScan.Filter(Controller.class))
@RequiredArgsConstructor
public class RootConfig {
//    @Value("${db.driverClassName}")
//    private String driverClassName;
    private final Environment environment;

    @Bean
    public DataSource dataSource() {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(
                environment.getProperty("db.driverClassName"));
        dataSource.setUrl(environment.getProperty("db.url"));
        dataSource.setUsername(environment.getProperty("db.username"));
        dataSource.setPassword(environment.getProperty("db.password"));

        dataSource.setInitialSize(Integer.parseInt(environment.getProperty("db.initialSize")));
        dataSource.setMaxTotal(Integer.parseInt(environment.getProperty("db.maxTotal")));
        dataSource.setMinIdle(Integer.parseInt(environment.getProperty("db.maxIdle")));
        dataSource.setMaxIdle(Integer.parseInt(environment.getProperty("db.minIdle")));

        dataSource.setMaxWaitMillis(Long.parseLong(environment.getProperty("db.maxWaitMillis")));
        dataSource.setValidationQuery(environment.getProperty("db.validationQuery"));
        dataSource.setTestOnBorrow(Boolean.parseBoolean(environment.getProperty("db.testOnBorrow")));
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);


        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/script/ddl.sql"));
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        dataSourceInitializer.setEnabled(true);
        dataSourceInitializer.afterPropertiesSet();
        return dataSource;
    }
}
