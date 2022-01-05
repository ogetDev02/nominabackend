package com.oget.ogetpro;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "segEntityManagerFactory", transactionManagerRef = "segTransactionManager", 
basePackages = { "com.oget.ogetpro.repository.seg" })
public class SegConfiguration {
	
	@Autowired
	private Environment env;
	
	@Bean
	public DataSource segDatasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("postgre.datasource.url"));
		dataSource.setUsername(env.getProperty("postgre.datasource.username"));
		dataSource.setPassword(env.getProperty("postgre.datasource.password"));
		dataSource.setDriverClassName(env.getProperty("postgre.datasource.driver-class-name"));
		
		return dataSource;
	}
	
	
	@Bean(name = "segEntityManagerFactory" )
	public LocalContainerEntityManagerFactoryBean segentityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(segDatasource());
		em.setPackagesToScan("com.oget.ogetpro.model.seg");
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("postgre.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.show-sql", env.getProperty("postgre.jpa.show-sql"));
		properties.put("hibernate.dialect", env.getProperty("postgre.jpa.database-platform"));
		
		em.setJpaPropertyMap(properties);
		
		return em;
		
	}
	
	
	@Bean(name = "segTransactionManager")
	public PlatformTransactionManager segTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(segentityManagerFactory().getObject());
		
		return transactionManager;
	}
}
