package com.gaur.datastore.config;

import java.sql.SQLException;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author Harshit Gaur <harshit@gaurs.in>
 * This initialises the NamedParameterJdbcTemplate with the configured properties. This also creates a table with
 * pre-set values.
 */
@Data
@Configuration
@ConfigurationProperties("application.datasource")
@Log4j2
public class DatabaseConfiguration {

    private String url;
    private String driver;
    private String username;
    private String password;

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        populateDatabase(jdbcTemplate);
        return jdbcTemplate;
    }

    private void populateDatabase(NamedParameterJdbcTemplate jdbcTemplate) {
        jdbcTemplate.getJdbcTemplate().execute(
            "CREATE TABLE IF NOT EXISTS data_store (id INT AUTO_INCREMENT PRIMARY KEY, key_name VARCHAR(200) NOT NULL, value VARCHAR(200) NOT NULL, creation_time TIMESTAMP)");
    }


}
