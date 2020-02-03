package com.fsoft.cme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fsoft.cme.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	@Query("select a from Account a where a.username = :username")
	public Account checkAccountUsername(@Param("username") String username);
	
	@Query("select a from Account a where a.email = :email")
	public Account checkAccountEmail(@Param("email") String email);
	
	@Query("select a from Account a where a.username = :username and a.password = :password")
	public Account checkAuthentication(@Param("username") String username, @Param("password") String password);
}
