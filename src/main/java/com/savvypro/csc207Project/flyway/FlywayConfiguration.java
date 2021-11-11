package com.savvypro.csc207Project.flyway;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

/**
 * Configure a flyway instance to load data after hibernate created schema from entities
 */
@Configuration
public class FlywayConfiguration {
    @Autowired
    public FlywayConfiguration(DataSource dataSource) {
        Flyway.configure().baselineOnMigrate(true).baselineVersion("0.0").dataSource(dataSource).load().migrate();
    }
}
