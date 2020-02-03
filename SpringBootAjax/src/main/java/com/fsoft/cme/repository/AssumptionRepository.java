package com.fsoft.cme.repository;

import com.fsoft.cme.entities.Assumption;
import com.fsoft.cme.entities.Category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AssumptionRepository extends JpaRepository<Assumption, Long> {
	
	@Query("from Category")
	List<Category> listCategory();
	
	@Modifying
	@Query(value = "insert into project_assumptions (project_id,assumptions_id) VALUES (:project_id, :assumption_id)", nativeQuery = true)
	@Transactional
	void addAssumptionRelationship(@Param("project_id") long project_id, @Param("assumption_id") long assumption_id);

	@Modifying
	@Query(value = "delete pa from project_assumptions pa where pa.project_id = :project_id and pa.assumptions_id = :assumption_id", nativeQuery = true)
	@Transactional
	void deleteAssumptionRelationship(@Param("project_id") long project_id, @Param("assumption_id") long assumption_id);
}
