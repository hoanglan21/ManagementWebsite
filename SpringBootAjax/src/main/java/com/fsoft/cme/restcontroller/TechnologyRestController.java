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

import com.fsoft.cme.dto.TechnologyDto;
import com.fsoft.cme.entities.Technology;
import com.fsoft.cme.exception.BaseException;
import com.fsoft.cme.exception.ExceptionCode;
import com.fsoft.cme.service.TechnologyService;

@RestController
@RequestMapping(value="/api/technology",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TechnologyRestController {
	@Autowired
	private TechnologyService technologyService;
	
	@GetMapping("")
	public List<Technology> getAllTechnology(){
		return technologyService.getAllTechnology();
	}
	
	@PostMapping("/add")
	public Technology createTechnology(@RequestBody TechnologyDto technologyDto) {
		//declare an entity to save data
		Technology technology = new Technology();
		
		//check if technology name is null
		if(technologyDto.getName() == null || technologyDto.getName().equals("")) {
			throw new BaseException(ExceptionCode.ER0013);
		}
		//check if technology name is existing
		if(technologyService.checkTechName(technologyDto.getName()) == false) {
			throw new BaseException(ExceptionCode.ER0019);
		}
		
		
		//save technology name
		technology.setName(technologyDto.getName());
		return technologyService.createTechnology(technology);
	}
	
	@GetMapping("/search/{name}")
	public List<Technology> getSearchTechnology(@PathVariable String name) {
		return technologyService.getSearchTechnology(name);
	}
}
