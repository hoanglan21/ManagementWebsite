package com.fsoft.cme.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fsoft.cme.entities.Customer;

@Service
public interface CustomerService {
	public List<Customer> getAll();
	
	public Customer createCustomer(Customer customer);
	
	public boolean checkCustomerName(String name);
	
	public boolean checkCustomerPhone(String phone);
	
	public boolean checkCustomerEmail(String email);
	
	public Customer findById(long id);
	
	public Customer editCustomer(Customer customer);
}
