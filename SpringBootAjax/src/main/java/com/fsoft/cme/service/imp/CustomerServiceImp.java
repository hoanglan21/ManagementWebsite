package com.fsoft.cme.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.cme.entities.Customer;
import com.fsoft.cme.exception.BaseException;
import com.fsoft.cme.exception.ExceptionCode;
import com.fsoft.cme.repository.CustomerRepository;
import com.fsoft.cme.service.CustomerService;

@Service
public class CustomerServiceImp implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

	@Override
	public boolean checkCustomerName(String name) {
		// TODO Auto-generated method stub
		if(customerRepository.checkCustomerName(name) == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkCustomerPhone(String phone) {
		// TODO Auto-generated method stub
		if(customerRepository.checkCustomerPhone(phone) == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkCustomerEmail(String email) {
		// TODO Auto-generated method stub
		if(customerRepository.checkCustomerEmail(email) == null) {
			return true;
		}
		return false;
	}

	@Override
	public Customer findById(long id) {
		// TODO Auto-generated method stub
		return customerRepository.findById(id).orElseThrow(() -> new BaseException(ExceptionCode.ER0033));
	}

	@Override
	public Customer editCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

}
