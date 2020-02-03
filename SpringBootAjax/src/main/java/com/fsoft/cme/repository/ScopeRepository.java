package com.fsoft.cme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.cme.entities.Scope;

@Repository
public interface ScopeRepository extends JpaRepository<Scope, Long> {
	@Query("select s from Scope s where s.name LIKE CONCAT('%',:name,'%') ")
	List<Scope> searchScope(@Param("name") String name);
	
	@Query("select s from Scope s where s.name = :name")
	Scope checkScopeName(@Param("name") String name);
}
