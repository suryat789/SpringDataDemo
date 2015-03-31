package com.demo.spring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.spring.data.entities.Address;
import com.demo.spring.data.entities.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	public Employee findByEmployeeId(Integer id);
	
	public Employee findEmployeeByAddress(Address address);
}
