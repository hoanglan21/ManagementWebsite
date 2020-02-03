package com.fsoft.cme.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.cme.entities.Constraints;

import com.fsoft.cme.exception.BaseException;
import com.fsoft.cme.exception.ExceptionCode;
import com.fsoft.cme.repository.ConstraintRepository;
import com.fsoft.cme.service.ConstraintService;

@Service
public class ConstraintServiceImp implements ConstraintService {

	@Autowired
	private ConstraintRepository repository;
	
	@Override
	public List<Constraints> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Constraints createConstraint(Constraints constraint, long projectId) {
		// TODO Auto-generated method stub
		Constraints constraintCtrl = repository.save(constraint);
		long constraintId= constraintCtrl.getConstraintId();
		repository.addConstraintRelationship(projectId, constraintId);
		return constraintCtrl;

	}

	@Override
	public Constraints editConstraint(Constraints constraint) {
		// TODO Auto-generated method stub
		return repository.save(constraint);
	}

	@Override
	public void deleteConstraint(long projectId,long constraintId) {
		// TODO Auto-generated method stub
		repository.deleteConstraintRelationship(projectId, constraintId);
		Constraints constraint = repository.findById(constraintId).orElseThrow(() -> new BaseException(ExceptionCode.ER0031));
		repository.delete(constraint);	
	}

	@Override
	public Constraints findById(long constraintId) {
		// TODO Auto-generated method stub
		return repository.findById(constraintId).orElseThrow(() -> new BaseException(ExceptionCode.ER0031));
	}
}
