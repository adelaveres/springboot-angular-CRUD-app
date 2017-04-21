package com.springboot.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name="Client")
public class Client implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(name="CNP",nullable=false)
	private String cnp;
	
	@NotNull
	@Column(name="IDNUMBER", nullable=false)
	private Integer IDNumber;
	
	@Column(name="NAME", nullable=false)
	private String name;
	
	@Column(name="ADDRESS", nullable=false)
	private String address;
	
	public Client(Integer id, String cnp, Integer idNumber, String name, String address){
		this.id = id;
		this.cnp = cnp;
		this.IDNumber = idNumber;
		this.name = name;
		this.address = address;
	}
	public Client(){
		
	}
	
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public String getCNP(){
		return this.cnp;
	}
	
	public void setCNP(String newCNP){
		this.cnp = newCNP;
	}
	
	public Integer getIDNumber(){
		return this.IDNumber;
	}
	
	public void setIDNumber(Integer newIDNumber){
		this.IDNumber = newIDNumber;
	}
	
	public String getName(){
		return this.name;
	}
	public void setName(String newName){
		this.name = newName;
	}
	public String getAddress(){
		return this.address;
	}
	public void setAddress(String newAddress){
		this.address = newAddress;
	}	

	
}
