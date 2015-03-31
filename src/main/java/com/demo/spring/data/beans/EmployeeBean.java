package com.demo.spring.data.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.spring.data.repository.EmployeeRepository;

@Component
public class EmployeeBean {
	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeBean() {
	}

	public EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}

	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

}
