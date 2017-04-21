package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.model.Employee;
import com.springboot.repositories.EmployeeRepository;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public List<Employee> findAllEmployees(){
		return employeeRepository.findAll();
	}
	
	public Employee findByName(String name){
		return employeeRepository.findByName(name);
	}
	
	public boolean existsEmployee(Employee e){
		return findByName(e.getName())!=null;
	}
	
	public void addEmployee(Employee e){
		employeeRepository.save(e);
	}
	
	public Employee findById(Integer id){
		return employeeRepository.findById(id);
	}
	
	public void updateEmployee(Integer id, String name, String cnp, String address){
		employeeRepository.updateEmployee(id, name, cnp, address);
	}
	
	public void deleteEmployeeById(Integer id){
		employeeRepository.deleteEmployeeById(id);
	}
}







