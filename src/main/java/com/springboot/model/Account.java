package com.springboot.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Account")
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "ACCOUNTID", nullable = false)
	private int accountId;
	
	@NotNull
	@Column(name = "CNP", nullable = false)
	private String cnp;
	
	@Column(name = "TYPE", nullable = false)
	private String type;
	
	@Column(name = "SUMMONEY", nullable = false)
	private int sumMoney;
	
	@Column(name = "CREATIONDATE", nullable = false)
	private Date creationDate;
	
	public Account(int id, String cnp, String type, int sumMoney, Date creationDate){
		this.accountId = id;
		this.cnp = cnp;
		this.type = type;
		this.sumMoney = sumMoney;
		this.creationDate = creationDate;
	}
	
	public Account(){	
	}
	
	public int getAccountId() {
		return this.accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getCNP() {
		return cnp;
	}

	public void setCNP(String cnp) {
		this.cnp = cnp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(int sumMoney) {
		this.sumMoney = sumMoney;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	
}
