package com.fsoft.cme.service;

import com.fsoft.cme.entities.Assumption;
import com.fsoft.cme.entities.Category;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
public interface AssumptionService {

    List<Assumption> getAll();

    Assumption getAssumptionById(Long id);

    Assumption add(Assumption assumption,long project_id);

    void delete(long assumption_id,long project_id);

    List<Category> listCategory();
    
    Assumption edit(Assumption assumption);
}
