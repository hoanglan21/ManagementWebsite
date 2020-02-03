package com.fsoft.cme.restcontroller;

import com.fsoft.cme.dto.AssumptionDto;
import com.fsoft.cme.dto.CategoryDto;

import com.fsoft.cme.entities.Assumption;
import com.fsoft.cme.entities.Category;

import com.fsoft.cme.exception.BaseException;
import com.fsoft.cme.exception.ExceptionCode;
import com.fsoft.cme.service.AssumptionService;
import com.fsoft.cme.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AssumptionRestController {

	@Autowired
	AssumptionService assumptionService;
	@Autowired
	private ProjectService projectService;

	@GetMapping(value = "/assumptions", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Assumption> getAll() {
		List<Assumption> list = assumptionService.getAll();
		return list;
	}

	@GetMapping(value = "/assumptions/categories", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Category> getAllCategory() {
		List<Category> list = assumptionService.listCategory();
		return list;
	}

	@PostMapping(value = "/assumption/add/{projectId}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public Assumption create(@RequestBody AssumptionDto assumptionDto, @PathVariable long projectId) {
		// declare an entity to save data
		Assumption assumption = new Assumption();
		// check if assumption description is null
		if (assumptionDto.getDescription() == null || assumptionDto.getDescription().equals("")) {
			throw new BaseException(ExceptionCode.ER0036);
		}
		// save assumption description to entity
		assumption.setDescription(assumptionDto.getDescription());

		// check if assumption reason is null
		if (assumptionDto.getReason() == null || assumptionDto.getReason().equals("")) {
			throw new BaseException(ExceptionCode.ER0037);
		}
		// save assumption reason to entity
		assumption.setReason(assumptionDto.getReason());

		// check if assumption categories is empty
		if (assumptionDto.getCategories().size() == 0) {
			throw new BaseException(ExceptionCode.ER0038);
		}
		// declare an array list to save category
		List<Category> assumptionCategories = new ArrayList<>();
		for (CategoryDto c : assumptionDto.getCategories()) {
			// check if assumption category id is null
			if (c.getCategoryId() == 0) {
				throw new BaseException(ExceptionCode.ER0039);
			} else {
				// add category to array list
				Category category = new Category();
				category.setCategoryId(c.getCategoryId());
				assumptionCategories.add(category);
			}
		}
		// save assumption categories to entity
		assumption.setCategories(assumptionCategories);

		// check if project id is existing
		if (projectService.findByID(projectId) == null) {
			throw new BaseException(ExceptionCode.ER0012);
		}
		return assumptionService.add(assumption, projectId);
	}

	@PutMapping(value = "/assumption/edit", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public Assumption update(@RequestBody AssumptionDto assumptionDto) {
		// declare an entity to save data
		Assumption assumption = new Assumption();
		//check if assumption id is null
		if(assumptionDto.getAssumptionId() == null) {
			throw new BaseException(ExceptionCode.ER0040);
		}
		//check if assumption id is existing
		if(assumptionService.getAssumptionById(assumptionDto.getAssumptionId()) == null) {
			throw new BaseException(ExceptionCode.ER0035);
		}
		//save assumption id to entity
		assumption.setAssumptionId(assumptionDto.getAssumptionId());
		
		// check if assumption description is null
		if (assumptionDto.getDescription() == null || assumptionDto.getDescription().equals("")) {
			throw new BaseException(ExceptionCode.ER0036);
		}
		// save assumption description to entity
		assumption.setDescription(assumptionDto.getDescription());

		// check if assumption reason is null
		if (assumptionDto.getReason() == null || assumptionDto.getReason().equals("")) {
			throw new BaseException(ExceptionCode.ER0037);
		}
		// save assumption reason to entity
		assumption.setReason(assumptionDto.getReason());

		// check if assumption categories is empty
		if (assumptionDto.getCategories().size() == 0) {
			throw new BaseException(ExceptionCode.ER0038);
		}
		// declare an array list to save category
		List<Category> assumptionCategories = new ArrayList<>();
		for (CategoryDto c : assumptionDto.getCategories()) {
			// check if assumption category id is null
			if (c.getCategoryId() == 0) {
				throw new BaseException(ExceptionCode.ER0039);
			} else {
				// add category to array list
				Category category = new Category();
				category.setCategoryId(c.getCategoryId());
				assumptionCategories.add(category);
			}
		}
		// save assumption categories to entity
		assumption.setCategories(assumptionCategories);

		return assumptionService.edit(assumption);
	}

	@DeleteMapping(value = "/assumption/delete/{assumptionId}/{projectId}", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public void delete(@PathVariable("assumptionId") long assumptionId, @PathVariable("projectId") long projectId) {
		// check if project id is existing
		if (projectService.findByID(projectId) == null) {
			throw new BaseException(ExceptionCode.ER0012);
		}
		assumptionService.delete(assumptionId, projectId);
	}

}
