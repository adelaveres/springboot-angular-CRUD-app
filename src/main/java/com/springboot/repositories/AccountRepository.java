package com.springboot.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.springboot.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{
	
	@Query("SELECT a FROM Account a WHERE a.cnp = ?1 ")
    List<Account> findClientAccounts(String cnp);

    @Modifying
    @Query("UPDATE Account a SET a.sumMoney = a.sumMoney + ?2 WHERE a.accountId = ?1")
    void updateAccount(int accountId, int sum);
    
    @Query("SELECT a FROM Account a WHERE a.accountId = ?1")
    Account findAccountById(int accountId);
    
    @Modifying
    @Query("DELETE FROM Account a WHERE a.accountId = ?1")
    void deleteAccount(int accountId);
    
}
