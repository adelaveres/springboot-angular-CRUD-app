package com.springboot.service;

import java.util.List;

import com.springboot.model.Employee;

public interface EmployeeService {
	
	List<Employee> findAllEmployees();
	
	Employee findByName(String name);
	
	boolean existsEmployee(Employee e);
	
	void addEmployee(Employee e);
	
	Employee findById(Integer id);
	
	void updateEmployee(Integer id, String name, String cnp, String address);
	
	void deleteEmployeeById(Integer id);
}
