package com.fsoft.cme.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.cme.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	@Query(value ="select * from project p where "
			+ "p.id in (select project_id from project_technologies pt where pt.technology_id = :technology_id) "
			+ "and p.id in (select project_id from project_scopes pc where pc.scope_id = :scope_id) "
			+ "and p.name like concat('%',:name,'%')", 
			countQuery = "select count(*) from project p where "
					+ "p.id in (select project_id from project_technologies pt where pt.technology_id = :technology_id) "
					+ "and p.id in (select project_id from project_scopes pc where pc.scope_id = :scope_id) "
					+ "and p.name like concat('%',:name,'%')", nativeQuery=true)
	Page<Project> getSearchProject(@Param("technology_id") long  technology_id, @Param("scope_id") long scope_id ,@Param("name") String name, Pageable pageable);
	
	@Query(value ="select * from project p where "
			+ "p.name LIKE CONCAT('%',:name,'%') ",
			countQuery = "select count(*) from project p where "
					+ "p.name LIKE CONCAT('%',:name,'%')", nativeQuery = true)
	Page<Project> getSearchProject(@Param("name") String name, Pageable pageable);
	
	
	@Query(value ="select * from project p where "
			+ "p.id in (select project_id from project_technologies pt where pt.technology_id = :technology_id) "
			+ "and p.name like concat('%',:name,'%')", 
			countQuery = "select count(*) from project p where "
					+ "p.id in (select project_id from project_technologies pt where pt.technology_id = :technology_id) "
					+ "and p.name like concat('%',:name,'%')", nativeQuery=true)
	Page<Project> getSearchProjectTechnology(@Param("technology_id") long  technology_id,@Param("name") String name,Pageable pageable);
	
	@Query(value ="select * from project p where "
			+ "and p.id in (select project_id from project_scopes pc where pc.scope_id = :scope_id) "
			+ "and p.name like concat('%',:name,'%') ", 
			countQuery = "select count(*) from project p where "
					+ "and p.id in (select project_id from project_scopes pc where pc.scope_id = :scope_id) "
					+ "and p.name like concat('%',:name,'%') ", nativeQuery=true)
	Page<Project> getSearchProjectScope(@Param("scope_id") long  scope_id,@Param("name") String name,Pageable pageable);
	
	
}
