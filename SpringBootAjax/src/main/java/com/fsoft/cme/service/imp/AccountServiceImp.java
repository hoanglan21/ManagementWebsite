package com.fsoft.cme.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.cme.entities.Account;
import com.fsoft.cme.repository.AccountRepository;
import com.fsoft.cme.service.AccountService;

@Service
public class AccountServiceImp implements AccountService {

	@Autowired
	private AccountRepository repository;
	
	@Override
	public Account createAccount(Account account) {
		// TODO Auto-generated method stub
		return repository.save(account);
	}

	@Override
	public boolean checkAccountUsername(String username) {
		// TODO Auto-generated method stub
		if(repository.checkAccountUsername(username) == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkAccountEmail(String email) {
		// TODO Auto-generated method stub
		if(repository.checkAccountEmail(email) == null) {
			return true;
		}
		return false;
	}

	@Override
	public Account checkAuthentication(String username, String password) {
		// TODO Auto-generated method stub
		return repository.checkAuthentication(username, password);
	}

}
