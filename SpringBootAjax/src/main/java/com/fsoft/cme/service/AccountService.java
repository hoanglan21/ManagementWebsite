package com.fsoft.cme.service;

import org.springframework.stereotype.Service;

import com.fsoft.cme.entities.Account;
@Service
public interface AccountService {
	public Account createAccount(Account account);
	
	public boolean checkAccountUsername(String username);
	
	public boolean checkAccountEmail(String email);
	
	public Account checkAuthentication(String username, String password);
}
