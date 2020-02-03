package com.fsoft.cme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.cme.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query("select c from Customer c where c.name = :name")
	Customer checkCustomerName(@Param("name") String name);
	
	@Query("select c from Customer c where c.phone = :phone")
	Customer checkCustomerPhone(@Param("phone") String phone);
	
	@Query("select c from Customer c where c.email = :email")
	Customer checkCustomerEmail(@Param("email") String email);
}
