package com.fsoft.cme.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.cme.entities.Constraints;

@Repository
@Transactional
public interface ConstraintRepository extends JpaRepository<Constraints, Long> {

	@Modifying
	@Query(value = "insert into project_constraints (project_id,constraints_id) VALUES (:project_id, :constraint_id)", nativeQuery = true)
	@Transactional
	void addConstraintRelationship(@Param("project_id") long project_id, @Param("constraint_id") long constraint_id);

	@Modifying
	@Query(value = "delete pc from project_constraints pc where pc.project_id = :project_id and pc.constraints_id = :constraint_id", nativeQuery = true)
	@Transactional
	void deleteConstraintRelationship(@Param("project_id") long project_id, @Param("constraint_id") long constraint_id);
}
