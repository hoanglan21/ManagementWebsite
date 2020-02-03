package com.fsoft.cme.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.cme.dto.ProjectDto;
import com.fsoft.cme.dto.ScopeDto;
import com.fsoft.cme.dto.TechnologyDto;
import com.fsoft.cme.entities.Project;
import com.fsoft.cme.entities.Scope;
import com.fsoft.cme.entities.Technology;
import com.fsoft.cme.exception.BaseException;
import com.fsoft.cme.exception.ExceptionCode;
import com.fsoft.cme.exception.ValidateMethod;
import com.fsoft.cme.service.CustomerService;
import com.fsoft.cme.service.ProjectService;

@RestController
@RequestMapping(value = "/api/project", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProjectRestController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private CustomerService cusService;

	@GetMapping("")
	public List<Project> getAll() {
		return projectService.getAll();
	}

	@PostMapping("/add")
	public Project createProject(@RequestBody ProjectDto projectDto) {
		// declare entity to save
		Project project = new Project();

		// validate and save data
		// check if project name is null
		if (projectDto.getName() == null || projectDto.getName().equals("")) {
			throw new BaseException(ExceptionCode.ER0002);
		}
		// save project name to entity
		project.setName(projectDto.getName());

		// check if project description is null
		if (projectDto.getDescription() == null || projectDto.getDescription().equals("")) {
			throw new BaseException(ExceptionCode.ER0003);
		}
		// save project description to entity
		project.setDescription(projectDto.getDescription());

		// check if project complexity is null
		if (projectDto.getComplexity() == null || projectDto.getComplexity().equals("")) {
			throw new BaseException(ExceptionCode.ER0004);
		}
		// check if project complexity contains invalid character
		if (ValidateMethod.checkNumericString(projectDto.getComplexity()) == false) {
			throw new BaseException(ExceptionCode.ER0015);
		}
		// check if project complexity is out of range
		if (Integer.parseInt(projectDto.getComplexity()) > 5 || Integer.parseInt(projectDto.getComplexity()) < 1) {
			throw new BaseException(ExceptionCode.ER0018);
		}
		// save project complexity to entity
		project.setComplexity(Integer.parseInt(projectDto.getComplexity()));

		// check if project size is null
		if (projectDto.getSize() == null || projectDto.getSize().equals("")) {
			throw new BaseException(ExceptionCode.ER0005);
		}
		// check if project size contains invalid character
		if (ValidateMethod.checkNumericString(projectDto.getSize()) == false) {
			throw new BaseException(ExceptionCode.ER0016);
		}

		// save project size to entity
		project.setSize(Integer.parseInt(projectDto.getSize()));

		// check if project warranty is null
		if (projectDto.getWarranty() == null || projectDto.getWarranty().equals("")) {
			throw new BaseException(ExceptionCode.ER0006);
		}
		// check if project warranty contains invalid character
		if (ValidateMethod.checkNumericString(projectDto.getWarranty()) == false) {
			throw new BaseException(ExceptionCode.ER0017);
		}
		// save project warranty to entity
		project.setWarranty(Integer.parseInt(projectDto.getWarranty()));

		// check if project customer id is null
		if (projectDto.getCustomer().getCustomerId() == 0) {
			throw new BaseException(ExceptionCode.ER0007);
		}
		// check if customer id is existing
		if (cusService.findById(projectDto.getCustomer().getCustomerId()) == null) {
			throw new BaseException(ExceptionCode.ER0033);
		}
		// save project customer to entity
		project.setCustomer(projectDto.getCustomer());

		// check if project technologies is empty
		if (projectDto.getTechnologies().size() == 0) {
			throw new BaseException(ExceptionCode.ER0009);
		}
		// declare an array list to save technology
		List<Technology> projectTechnologies = new ArrayList<>();
		for (TechnologyDto t : projectDto.getTechnologies()) {
			// check if project technology id is null
			if (t.getTechnologyId() == 0) {
				throw new BaseException(ExceptionCode.ER0008);
			} else {
				// add technology to array list
				Technology tech = new Technology();
				tech.setTechnologyId(t.getTechnologyId());
				projectTechnologies.add(tech);
			}
		}
		// save project technologies to entity
		project.setTechnologies(projectTechnologies);

		// check if project scope is empty
		if (projectDto.getScopes().size() == 0) {
			throw new BaseException(ExceptionCode.ER0010);
		}
		// declare an array list to save scope
		List<Scope> projectScopes = new ArrayList<>();
		for (ScopeDto s : projectDto.getScopes()) {
			// check if project scope id is null
			if (s.getScopeId() == 0) {
				throw new BaseException(ExceptionCode.ER0011);
			} else {
				// save scope to array list
				Scope scope = new Scope();
				scope.setScopeId(s.getScopeId());
				projectScopes.add(scope);
			}
		}
		// save project scope to entity
		project.setScopes(projectScopes);

		return projectService.createProject(project);
	}

	@GetMapping("/{id}")
	public Project findByID(@PathVariable long id) {
		return projectService.findByID(id);
	}

	@PutMapping("/edit")
	public Project editProject(@RequestBody ProjectDto projectDto) {
		// declare entity to save
		Project project = new Project();

		// validate and save data
		// check if project id is null
		if (projectDto.getProjectId() == null) {
			throw new BaseException(ExceptionCode.ER0012);
		}
		// check if project id is exist
		project = projectService.findByID(projectDto.getProjectId());
		// save project id to entity
		project.setProjectId(projectDto.getProjectId());

		// check if project name is null
		if (projectDto.getName() == null || projectDto.getName().equals("")) {
			throw new BaseException(ExceptionCode.ER0002);
		}
		// save project name to entity
		project.setName(projectDto.getName());

		// check if project description is null
		if (projectDto.getDescription() == null || projectDto.getDescription().equals("")) {
			throw new BaseException(ExceptionCode.ER0003);
		}
		// save project description to entity
		project.setDescription(projectDto.getDescription());

		// check if project complexity is null
		if (projectDto.getComplexity() == null || projectDto.getComplexity().equals("")) {
			throw new BaseException(ExceptionCode.ER0004);
		}
		// check if project complexity contains invalid character
		if (ValidateMethod.checkNumericString(projectDto.getComplexity()) == false) {
			throw new BaseException(ExceptionCode.ER0015);
		}
		// check if project complexity is out of range
		if (Integer.parseInt(projectDto.getComplexity()) > 5 || Integer.parseInt(projectDto.getComplexity()) < 1) {
			throw new BaseException(ExceptionCode.ER0018);
		}
		// save project complexity to entity
		project.setComplexity(Integer.parseInt(projectDto.getComplexity()));

		// check if project size is null
		if (projectDto.getSize() == null || projectDto.getSize().equals("")) {
			throw new BaseException(ExceptionCode.ER0005);
		}
		// check if project size contains invalid character
		if (ValidateMethod.checkNumericString(projectDto.getSize()) == false) {
			throw new BaseException(ExceptionCode.ER0016);
		}
		// save project size to entity
		project.setSize(Integer.parseInt(projectDto.getSize()));

		// check if project warranty is null
		if (projectDto.getWarranty() == null || projectDto.getWarranty().equals("")) {
			throw new BaseException(ExceptionCode.ER0006);
		}
		// check if project warranty contains invalid character
		if (ValidateMethod.checkNumericString(projectDto.getWarranty()) == false) {
			throw new BaseException(ExceptionCode.ER0017);
		}
		// save project warranty to entity
		project.setWarranty(Integer.parseInt(projectDto.getWarranty()));

		// check if project customer id is null
		if (projectDto.getCustomer().getCustomerId() == 0) {
			throw new BaseException(ExceptionCode.ER0007);
		}
		// check if customer id is existing
		if (cusService.findById(projectDto.getCustomer().getCustomerId()) == null) {
			throw new BaseException(ExceptionCode.ER0033);
		}
		// save project customer to entity
		project.setCustomer(projectDto.getCustomer());

		// check if project technologies is empty
		if (projectDto.getTechnologies().size() == 0) {
			throw new BaseException(ExceptionCode.ER0009);
		}
		// declare an array list to save technology
		List<Technology> projectTechnologies = new ArrayList<>();
		for (TechnologyDto t : projectDto.getTechnologies()) {
			// check if project technology id is null
			if (t.getTechnologyId() == 0) {
				throw new BaseException(ExceptionCode.ER0008);
			} else {
				// add technology to array list
				Technology tech = new Technology();
				tech.setTechnologyId(t.getTechnologyId());
				projectTechnologies.add(tech);
			}
		}
		// save project technologies to entity
		project.setTechnologies(projectTechnologies);

		// check if project scope is empty
		if (projectDto.getScopes().size() == 0) {
			throw new BaseException(ExceptionCode.ER0010);
		}
		// declare an array list to save scope
		List<Scope> projectScopes = new ArrayList<>();
		for (ScopeDto s : projectDto.getScopes()) {
			// check if project scope id is null
			if (s.getScopeId() == 0) {
				throw new BaseException(ExceptionCode.ER0011);
			} else {
				// save scope to array list
				Scope scope = new Scope();
				scope.setScopeId(s.getScopeId());
				projectScopes.add(scope);
			}
		}
		// save project scope to entity
		project.setScopes(projectScopes);
		
		
		return projectService.editProject(project);
	}

	@DeleteMapping("/delete/{projectId}")
	public void deleteProject(@PathVariable long projectId) {
		projectService.deleteProject(projectId);
	}

	@GetMapping("/page/{page}")
	public Page<Project> getPageProject(@PathVariable int page) {
		int size = 10;
		return projectService.getPageProject(page, size);
	}

	@GetMapping("/search")
	public Page<Project> getSearchProject(@RequestParam("page") int page, @RequestParam("techId") long techId,
			@RequestParam("scopeId") long scopeId, @RequestParam("name") String name) {
		int size = 10;
		if (name == null || name.equals("")) {
			return projectService.getSearchProjectPage(page, size, techId, scopeId);
		} else {
			return projectService.getSearchProjectPage(page, size, techId, scopeId, name);
		}

	}
}
