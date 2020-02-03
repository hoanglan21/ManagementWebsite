package com.fsoft.cme.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.fsoft.cme.entities.Project;

@Service
public interface ProjectService {
	public List<Project> getAll();
	
	public Project createProject(Project project );
	
	public Project findByID(long id);
	
	public Project editProject(Project project);
	
	public void deleteProject(long id);
	
	public Page<Project> getPageProject(int page, int size);
	
	public Page<Project>getSearchProjectPage(int page, int size,long tech_id,long scope_id);
	
	public Page<Project>getSearchProjectPage(int page, int size,long tech_id,long scope_id,String name);
}
