package com.oget.ogetpro;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "nomEntityManagerFactory", transactionManagerRef = "nomTransactionManager", 
basePackages = { "com.oget.ogetpro.repository" })

public class NomConfiguration {
	
	@Autowired
	private Environment env;
	
	@Bean
	public DataSource nomDatasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		
		return dataSource;
	}
	
	@Primary
	@Bean(name = "nomEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean nomentityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(nomDatasource());
		em.setPackagesToScan("com.oget.ogetpro.model");
	
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		//vendorAdapter.setShowSql(true);
		//vendorAdapter.setGenerateDdl(false);
		em.setJpaVendorAdapter(vendorAdapter);
		
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.show-sql", env.getProperty("spring.jpa.properties.hibernate.show_sql"));
		properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		em.setJpaPropertyMap(properties);
		
		return em;
		
	}
	
	@Primary
	@Bean(name = "nomTransactionManager")
	public PlatformTransactionManager nomTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(nomentityManagerFactory().getObject());
		
		return transactionManager;
	}
}
