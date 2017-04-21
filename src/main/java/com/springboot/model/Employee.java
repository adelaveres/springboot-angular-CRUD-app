package com.springboot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Employee")
public class Employee implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer employeeId;
	
	@Column(name="NAME", nullable=false)
	private String name;
	
	@NotNull
	@Column(name="CNP",nullable=false)
	private String cnp;
	
	@Column(name="ADDRESS", nullable=false)
	private String address;
	
	public Employee(Integer id, String name, String cnp, String address){
		this.employeeId = id;
		this.name = name;
		this.cnp = cnp;
		this.address = address;
	}
	public Employee(){}
	
	public Integer getEmployeeId(){
		return this.employeeId;
	}
	public void setEmployeeId(Integer employeeId){
		this.employeeId = employeeId;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getCNP(){
		return this.cnp;
	}
	public void setCNP(String cnp){
		this.cnp = cnp;
	}
	public String getAddress(){
		return this.address;
	}
	public void setAddress(String address){
		this.address = address;
	}
}
