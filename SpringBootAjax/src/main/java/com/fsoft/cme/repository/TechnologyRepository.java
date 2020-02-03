package com.fsoft.cme.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.cme.entities.Technology;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long>{
	
	@Query("select t from Technology t where t.name LIKE CONCAT('%',:name,'%') ")
	List<Technology> searchTechnology(@Param("name") String name);
	
	@Query("select t from Technology t where t.name = :name")
	Technology checkTechName(@Param("name") String name);
}
