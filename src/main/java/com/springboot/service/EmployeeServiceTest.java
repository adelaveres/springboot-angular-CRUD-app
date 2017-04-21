package com.springboot.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.model.Employee;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "prod")
public class EmployeeServiceTest {
	
	@Autowired
	EmployeeService employeeService;
	
	int initNoEmpl;
	
	@Before
	public void setUp(){
		initNoEmpl = employeeService.findAllEmployees().size();
	}
	
	@After
	public void tearDown(){
		int finalNoEmpl = employeeService.findAllEmployees().size();
		int diff = finalNoEmpl-initNoEmpl;
		for(int i = 0; i<diff; i++){
			employeeService.deleteEmployeeById(finalNoEmpl);
			finalNoEmpl = finalNoEmpl-1;
		}
	}
	
	@Test
	public void findByIdTest(){
		assertNotNull(employeeService.findById(4));
	}
	@Test
	public void findByNameTest(){
		assertNotNull(employeeService.findByName("Horea Nicolescu"));
	}
	
	@Test
	public void addEmployeeTest(){
		Integer employeeNo = employeeService.findAllEmployees().size();
		employeeService.addEmployee(new Employee(employeeNo+1, "Mirela Costin", "4309430943095", "str. Ploiesti, nr. 284"));
		
		System.out.println("\nafter Add:\n");
		List<Employee>employees = employeeService.findAllEmployees();
		for(Employee e: employees){
			System.out.println(e.getEmployeeId()+"\t"+e.getName());
		}
		
		int id = employeeService.findByName("Mirela Costin").getEmployeeId();
		assertNotNull(employeeService.findById(id));
	}
	
	@Test
	public void updateEmployeeTest(){
		Integer employeeNo = employeeService.findAllEmployees().size();
		employeeService.addEmployee(new Employee(employeeNo+2, "Marin Preda", "4009400940092", "str. Pitagora, nr. 314"));
		int id = employeeService.findByName("Marin Preda").getEmployeeId();
		employeeService.updateEmployee(id, "Maria Preda", "4009400940092", "str. Pitagora, nr. 314");
		
		System.out.println("\nafter Update:\n");
		List<Employee>employees = employeeService.findAllEmployees();
		for(Employee e: employees){
			System.out.println(e.getEmployeeId()+"\t"+e.getName());
		}
		
		assert(employeeService.findByName("Maria Preda").getEmployeeId()==id);
	}
	
	@Test
	public void findAllEmployeesTest(){
		assertNotNull(employeeService.findAllEmployees());
	}
	
	@Test
	public void deleteEmployeeByIdTest(){
		Integer employeeNo = employeeService.findAllEmployees().size();
		employeeService.addEmployee(new Employee(employeeNo+1, "Liviu Petrachioaie", "1109110911094", "str. Turnisor, nr. 219"));
		int id = employeeService.findByName("Liviu Petrachioaie").getEmployeeId();
		employeeService.deleteEmployeeById(id);
		
		System.out.println("\nafter Delete:\n");
		List<Employee>employees = employeeService.findAllEmployees();
		for(Employee e: employees){
			System.out.println(e.getEmployeeId()+"\t"+e.getName());
		}
		assertNull(employeeService.findById(id));
	}
	
	@Test
	public void existsEmployeeTest(){
		assert(employeeService.existsEmployee(employeeService.findById(4)));
	}
}
