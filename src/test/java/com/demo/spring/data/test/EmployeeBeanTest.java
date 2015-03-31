package com.demo.spring.data.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.spring.data.beans.EmployeeBean;
import com.demo.spring.data.config.AppConfig;
import com.demo.spring.data.entities.Address;
import com.demo.spring.data.entities.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeBeanTest {

	@Autowired
	EmployeeBean employeeBean;

	private static Employee employee;

	@BeforeClass
	public static void prepareEmpData() {
		employee = new Employee();
		employee.setEmployeeId(1);
		employee.setEmployeeName("Steven King");

		Address address = new Address();
		address.setAddressId(1);
		address.setAddressCountry("United Kingdom");
		address.setAddressCity("London");
		address.setEmployee(employee);
		employee.setAddress(address);
	}

	@Test
	public void test1EmployeeSave() {
		Employee tempEmployee = employeeBean.getEmployeeRepository().save(employee);
		Assert.assertEquals("Employee instance not same", employee, tempEmployee);
		Assert.assertEquals("Employee Address instances not same", employee.getAddress(), tempEmployee.getAddress());
	}

	@Test
	public void test2FindEmployeeById() {
		Employee tempEmployee = employeeBean.getEmployeeRepository().findByEmployeeId(employee.getEmployeeId());
		Assert.assertEquals("Employee instance not same", employee, tempEmployee);
		Assert.assertEquals("Employee Address instances not same", employee.getAddress(), tempEmployee.getAddress());
	}
	
	@Test
	public void test3FindEmployeeByAddress() {
		Employee tempEmployee = employeeBean.getEmployeeRepository().findEmployeeByAddress(employee.getAddress());
		Assert.assertEquals("Employee instance not same", employee, tempEmployee);
		Assert.assertEquals("Employee Address instances not same", employee.getAddress(), tempEmployee.getAddress());
	}

	@Test
	public void test4UpdateEmployee() {
		employee.setEmployeeName("Surya");
		Employee tempEmployee = employeeBean.getEmployeeRepository().save(employee);

		Assert.assertEquals("Employee instance not same", employee, tempEmployee);
		Assert.assertEquals("Employee Address instances not same", employee.getAddress(), tempEmployee.getAddress());
	}

	@Test
	public void test5DeleteEmployee() {
		employeeBean.getEmployeeRepository().delete(employee);
		Employee tempEmployee = employeeBean.getEmployeeRepository().findByEmployeeId(employee.getEmployeeId());
		Assert.assertNull("Employee instance should be Null", tempEmployee);
	}
}
