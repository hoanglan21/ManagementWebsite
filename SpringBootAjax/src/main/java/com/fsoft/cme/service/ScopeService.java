package com.fsoft.cme.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.fsoft.cme.entities.Scope;

@Service
public interface ScopeService {
	public List<Scope> getAllScope();
	
	public Scope createScope(Scope scope); 
	
	public List<Scope> getSearchScope(String name);
	
	public boolean checkScopeName(String name);
}
