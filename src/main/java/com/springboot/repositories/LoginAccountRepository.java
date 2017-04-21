package com.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.model.LoginAccount;

@Repository
public interface LoginAccountRepository extends JpaRepository<LoginAccount, Long>{
	
	@Query("SELECT ac FROM LoginAccount ac WHERE ac.username = ?1")
	LoginAccount findByUsername(String username);
}
