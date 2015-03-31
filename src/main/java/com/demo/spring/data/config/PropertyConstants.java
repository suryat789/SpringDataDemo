package com.demo.spring.data.config;

import com.demo.spring.data.entities.Employee;

public interface PropertyConstants {

	String JDBC_DRIVER_CLASS = "jdbc.driverClassName";
	String JDBC_URL = "jdbc.url";
	String JDBC_USERNAME = "jdbc.username";
	String JDBC_PASSWORD = "jdbc.password";
	
	String HIBERNATE_DIALECT = "hibernate.dialect";
	String HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	String HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	
	String ENTITY_PKG_TO_SCAN = Employee.class.getPackage().getName();
}
