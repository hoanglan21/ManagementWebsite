package com.fsoft.cme.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.cme.dto.ScopeDto;
import com.fsoft.cme.entities.Scope;
import com.fsoft.cme.exception.BaseException;
import com.fsoft.cme.exception.ExceptionCode;
import com.fsoft.cme.service.ScopeService;

@RestController
@RequestMapping(value="api/scope",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ScopeRestController {
	
	@Autowired
	private ScopeService service;
	
	@GetMapping("")
	public List<Scope> getAllScope(){
		return service.getAllScope();
	}
	
	@PostMapping("/add")
	public Scope createScope(@RequestBody ScopeDto scopeDto) {
		//declare entity to save data
		Scope scope = new Scope();
		//check if scope name is null
		if(scopeDto.getName() == null || scopeDto.getName().equals("")) {
			throw new BaseException(ExceptionCode.ER0014);
		}
		//check if scope name is existing
		if(service.checkScopeName(scopeDto.getName()) == false) {
			throw new BaseException(ExceptionCode.ER0020);
		}
		
		//save scope name to entity
		scope.setName(scopeDto.getName());
		
		return service.createScope(scope);
	}
	
	@GetMapping("/search/{name}")
	public List<Scope> getSearchScope(@PathVariable String name){
		return service.getSearchScope(name);
	}
}
