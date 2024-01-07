//package com.example.springdatajpa02.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl("jdbc:mysql://localhost:3306/spring_data_demo_01");
//        dataSource.setUsername("root");
//        dataSource.setPassword("123qwe!@#");
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        return dataSource;
//    }
//
//    @Bean
//    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
//        return new NamedParameterJdbcTemplate(dataSource);
//    }
//
//
//}
