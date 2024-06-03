package com.springbacth.MigracaoDadosJob.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSouceConfig {


    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource") //pega as configurações do properties pra criar o banco
    public DataSource springDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "app.datasource") //pega as configurações do properties pra criar o banco
    public DataSource appDataSource(){
        return DataSourceBuilder.create().build();
    }


}
