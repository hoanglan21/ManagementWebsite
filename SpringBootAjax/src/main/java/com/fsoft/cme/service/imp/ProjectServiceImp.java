package com.fsoft.cme.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fsoft.cme.entities.Project;
import com.fsoft.cme.exception.BaseException;
import com.fsoft.cme.exception.ExceptionCode;
import com.fsoft.cme.repository.ProjectRepository;
import com.fsoft.cme.service.ProjectService;

@Service
public class ProjectServiceImp implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Override
	public List<Project> getAll() {
		// TODO Auto-generated method stub
		return projectRepository.findAll();
	}

	@Override
	public Project createProject(Project project) throws BaseException {
		// TODO Auto-generated method stub
		return projectRepository.save(project);
	}

	@Override
	public Project findByID(long id) {
		// TODO Auto-generated method stub
		
		return projectRepository.findById(id).orElseThrow(() -> new BaseException(ExceptionCode.ER0012));
	}

	@Override
	public Project editProject(Project project) {
		// TODO Auto-generated method stub
		
		return projectRepository.save(project);
	}

	@Override
	public void deleteProject(long id) {
		// TODO Auto-generated method stub
		Project project = projectRepository.findById(id).orElseThrow(() -> new BaseException(ExceptionCode.ER0012));
		projectRepository.delete(project);
	}

	@Override
	public Page<Project> getPageProject(int page, int size) {
		// TODO Auto-generated method stub
		return projectRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public Page<Project> getSearchProjectPage(int page, int size, long tech_id, long scope_id) {
		// TODO Auto-generated method stub
		if(tech_id == 0 && scope_id == 0) {
			return projectRepository.findAll(PageRequest.of(page, size));
		}
		
		if(tech_id == 0 && scope_id != 0) {
			return projectRepository.getSearchProjectScope(scope_id, "", PageRequest.of(page, size));
		}
		
		if(scope_id == 0 && tech_id != 0) {
			return projectRepository.getSearchProjectTechnology(tech_id, "", PageRequest.of(page, size));
		}
		
		return projectRepository.getSearchProject(tech_id, scope_id, "", PageRequest.of(page, size));
	
	}

	@Override
	public Page<Project> getSearchProjectPage(int page, int size, long tech_id, long scope_id, String name) {
		// TODO Auto-generated method stub
		if(tech_id == 0 && scope_id == 0) {
			return projectRepository.getSearchProject(name, PageRequest.of(page, size));
		}
		
		if(tech_id == 0 && scope_id != 0) {
			return projectRepository.getSearchProjectScope(scope_id, name, PageRequest.of(page, size));
		}
		
		if(scope_id == 0 && tech_id != 0) {
			return projectRepository.getSearchProjectTechnology(tech_id, name, PageRequest.of(page, size));
		}
		
		return projectRepository.getSearchProject(tech_id, scope_id, name, PageRequest.of(page, size));
		
	}


	
}
