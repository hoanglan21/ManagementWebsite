package com.fsoft.cme.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fsoft.cme.entities.Constraints;

@Service
public interface ConstraintService {
	public List<Constraints> getAll();
		
	public Constraints createConstraint(Constraints constraint, long project_id);
	
	public Constraints editConstraint(Constraints constraint);
	
	public void deleteConstraint(long project_id,long constraint_id);
	
	public Constraints findById(long constraint_id);
	
}
