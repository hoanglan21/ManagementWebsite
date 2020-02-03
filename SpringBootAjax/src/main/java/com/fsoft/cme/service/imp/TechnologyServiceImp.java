package com.fsoft.cme.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.fsoft.cme.entities.Technology;

import com.fsoft.cme.repository.TechnologyRepository;
import com.fsoft.cme.service.TechnologyService;

@Service
public class TechnologyServiceImp implements TechnologyService {

	@Autowired
	private TechnologyRepository technologyRepository;
	
	@Override
	public List<Technology> getAllTechnology() {
		// TODO Auto-generated method stub
		return technologyRepository.findAll();
	}

	@Override
	public Technology createTechnology(Technology technology) {
		// TODO Auto-generated method stub
		return technologyRepository.save(technology);
	}

	@Override
	public List<Technology> getSearchTechnology(String name) {
		// TODO Auto-generated method stub
		return technologyRepository.searchTechnology(name);
	}

	@Override
	public boolean checkTechName(String name) {
		// TODO Auto-generated method stub
		Technology technology = technologyRepository.checkTechName(name);
		if(technology != null) {
			return false;
		}
		return true;
	}
	
}
