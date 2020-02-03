package com.fsoft.cme.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.cme.dto.ConstraintsDto;
import com.fsoft.cme.entities.Constraints;
import com.fsoft.cme.exception.BaseException;
import com.fsoft.cme.exception.ExceptionCode;
import com.fsoft.cme.service.ConstraintService;
import com.fsoft.cme.service.ProjectService;

@RestController
@RequestMapping(value = "/api/constraint", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ConstraintRestController {
	@Autowired
	private ConstraintService service;
	@Autowired
	private ProjectService projectService;

	@GetMapping("")
	public List<Constraints> getAll() {
		return service.getAll();
	}
	
	@PostMapping("/add/{projectId}")
	public Constraints createConstraint(@RequestBody ConstraintsDto constraintDto, @PathVariable long projectId) {
		// declare an entity to save constraint
		Constraints constraint = new Constraints();
		// check if constraint description is null
		if (constraintDto.getDescription() == null || constraintDto.getDescription().equals("")) {
			throw new BaseException(ExceptionCode.ER0030);
		}
		// save constraint description to entity
		constraint.setDescription(constraintDto.getDescription());

		// check if project id is existing
		if (projectService.findByID(projectId) == null) {
			throw new BaseException(ExceptionCode.ER0012);
		}
		return service.createConstraint(constraint, projectId);
	}

	@PutMapping("/edit")
	public Constraints editConstraint(@RequestBody ConstraintsDto constraintDto) {
		// declare an entity to save constraint
		Constraints constraint = new Constraints();
		//check if constraint id is null
		if(constraintDto.getConstraintId() == null) {
			throw new BaseException(ExceptionCode.ER0032);
		}
		//check if constraint id is existing
		if(service.findById(constraintDto.getConstraintId()) == null) {
			throw new BaseException(ExceptionCode.ER0031);
		}
		//save constraint id to entity
		constraint.setConstraintId(constraintDto.getConstraintId());
		
		// check if constraint description is null
		if (constraintDto.getDescription() == null || constraintDto.getDescription().equals("")) {
			throw new BaseException(ExceptionCode.ER0030);
		}
		// save constraint description to entity
		constraint.setDescription(constraintDto.getDescription());

		return service.editConstraint(constraint);
	}

	@DeleteMapping("/delete/{constraintId}/{projectId}")
	public void deleteConstraint(@PathVariable long constraintId, @PathVariable long projectId) {
		// check if project id is existing
		if (projectService.findByID(projectId) == null) {
			throw new BaseException(ExceptionCode.ER0012);
		}
		service.deleteConstraint(projectId, constraintId);
	}
}
