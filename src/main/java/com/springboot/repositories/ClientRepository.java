package com.springboot.repositories;

import com.springboot.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByName(String name);
    
    @Query("SELECT c FROM Client c WHERE c.id = ?1")
    Client findById(Integer id);

    @Modifying
    @Query("DELETE FROM Client c WHERE c.id = ?1")
	void delete(Integer id);
    
}







