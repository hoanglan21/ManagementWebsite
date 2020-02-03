package com.fsoft.cme.service.imp;

import com.fsoft.cme.entities.Assumption;
import com.fsoft.cme.entities.Category;
import com.fsoft.cme.exception.BaseException;
import com.fsoft.cme.exception.ExceptionCode;
import com.fsoft.cme.repository.AssumptionRepository;
import com.fsoft.cme.service.AssumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AssumptionServiceImpl implements AssumptionService {

    @Autowired
    private AssumptionRepository assumptionRepository;

    @Override
    public List<Assumption> getAll() {
        return assumptionRepository.findAll();
    }

    @Override
    public Assumption getAssumptionById(Long id) {
        return assumptionRepository.findById(id).orElseThrow(()->new BaseException(ExceptionCode.ER0035));
    }

    @Override
    public Assumption add(Assumption assumption,long projectId) {
    	
        Assumption response = assumptionRepository.save(assumption);
        assumptionRepository.addAssumptionRelationship(projectId, response.getAssumptionId());
        return response;
    }

    @Override
    public void delete(long assumptionId,long projectId) {
    	Assumption assumption = assumptionRepository.findById(assumptionId).orElseThrow(()-> new BaseException(ExceptionCode.ER0035));
    	assumptionRepository.deleteAssumptionRelationship(projectId, assumptionId);
        assumptionRepository.delete(assumption);
    }

	@Override
	public List<Category> listCategory() {
		// TODO Auto-generated method stub
		return assumptionRepository.listCategory();
	}

	@Override
	public Assumption edit(Assumption assumption) {
		// TODO Auto-generated method stub
		return assumptionRepository.save(assumption);
	}


}
