package com.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
	
	Employee findByName(String name);
	
	@Query("SELECT e FROM Employee e WHERE e.employeeId = ?1")
	Employee findById(Integer id);
	
	
	@Modifying
	@Query("DELETE FROM Employee e WHERE e.id = ?1")
	void deleteEmployeeById(Integer id);
	
	@Modifying
    @Query("UPDATE Employee e SET e.name=?2, e.cnp=?3, e.address=?4 WHERE e.employeeId = ?1")
	void updateEmployee(Integer id, String name, String cnp, String address);
}
