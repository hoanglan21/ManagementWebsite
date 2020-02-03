package com.fsoft.cme.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.fsoft.cme.entities.Technology;

@Service
public interface TechnologyService {
	public List<Technology> getAllTechnology();
	
	public Technology createTechnology(Technology technology);
	
	public boolean checkTechName(String name);
	
	public List<Technology> getSearchTechnology(String name);
}
