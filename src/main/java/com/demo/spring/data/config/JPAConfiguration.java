package com.demo.spring.data.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static com.demo.spring.data.config.PropertyConstants.*;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.demo.spring.data.config" })
@EnableJpaRepositories("com.demo.spring.data.repository")
@PropertySource(value = { "classpath:application.properties" })
public class JPAConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty(JDBC_DRIVER_CLASS));
		dataSource.setUrl(environment.getRequiredProperty(JDBC_URL));
		dataSource.setUsername(environment.getRequiredProperty(JDBC_USERNAME));
		dataSource.setPassword(environment.getRequiredProperty(JDBC_PASSWORD));
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws ClassNotFoundException {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan(ENTITY_PKG_TO_SCAN);
		entityManagerFactoryBean.setPersistenceProvider(new HibernatePersistenceProvider());
		entityManagerFactoryBean.setJpaProperties(hibernateProperties());
		return entityManagerFactoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager() throws ClassNotFoundException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put(HIBERNATE_DIALECT, environment.getRequiredProperty(HIBERNATE_DIALECT));
		properties.put(HIBERNATE_SHOW_SQL, environment.getRequiredProperty(HIBERNATE_SHOW_SQL));
		properties.put(HIBERNATE_FORMAT_SQL, environment.getRequiredProperty(HIBERNATE_FORMAT_SQL));
		return properties;
	}
}