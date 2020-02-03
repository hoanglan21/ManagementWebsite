package com.fsoft.cme.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.fsoft.cme.entities.Scope;
import com.fsoft.cme.exception.BaseException;
import com.fsoft.cme.repository.ScopeRepository;
import com.fsoft.cme.service.ScopeService;

@Service
public class ScopeServiceImp implements ScopeService {

	@Autowired
	private ScopeRepository repository;
	
	@Override
	public List<Scope> getAllScope() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Scope createScope(Scope scope) throws BaseException {
		// TODO Auto-generated method stub
		return repository.save(scope);
	}

	@Override
	public List<Scope> getSearchScope(String name) {
		// TODO Auto-generated method stub
		return repository.searchScope(name);
	}

	@Override
	public boolean checkScopeName(String name) {
		// TODO Auto-generated method stub
		if(repository.checkScopeName(name) != null) {
			return false;
		}
		return true;
	}

}
