package com.fsoft.cme.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.cme.dto.CustomerDto;
import com.fsoft.cme.entities.Customer;
import com.fsoft.cme.exception.BaseException;
import com.fsoft.cme.exception.ExceptionCode;
import com.fsoft.cme.exception.ValidateMethod;
import com.fsoft.cme.service.CustomerService;

@RestController
@RequestMapping(value = "/api/customer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CustomerRestController {
	@Autowired
	private CustomerService customerService;

	@GetMapping("")
	public List<Customer> getAll() {
		return customerService.getAll();
	}

	@GetMapping("/{customerId}")
	public Customer findById(@PathVariable long customerId) {
		return customerService.findById(customerId);
	}

	@PostMapping("/add")
	public Customer createCustomer(@RequestBody CustomerDto customerDto) {
		// declare an entity to save data
		Customer customer = new Customer();

		// check if customer name is null
		if (customerDto.getName() == null || customerDto.getName().equals("")) {
			throw new BaseException(ExceptionCode.ER0021);
		}
		// check if customer name is existing
		if (customerService.checkCustomerName(customerDto.getName()) == false) {
			throw new BaseException(ExceptionCode.ER0022);
		}
		// save customer name to entity
		customer.setName(customerDto.getName());

		// check if customer phone is null
		if (customerDto.getPhone() == null || customerDto.getPhone().equals("")) {
			throw new BaseException(ExceptionCode.ER0023);
		}
		// check if customer phone contains invalid character
		if (ValidateMethod.checkNumericString(customerDto.getPhone()) == false) {
			throw new BaseException(ExceptionCode.ER0024);
		}
		// check if customer phone is existing
		if (customerService.checkCustomerPhone(customerDto.getPhone()) == false) {
			throw new BaseException(ExceptionCode.ER0025);
		}
		// save customer phone to entity
		customer.setPhone(customerDto.getPhone());

		// check if customer email is null
		if (customerDto.getEmail() == null || customerDto.getEmail().equals("")) {
			throw new BaseException(ExceptionCode.ER0026);
		}
		// check if customer email is invalid
		if (ValidateMethod.validateEmail(customerDto.getEmail()) == false) {
			throw new BaseException(ExceptionCode.ER0027);
		}
		// check if customer email is existing
		if (customerService.checkCustomerEmail(customerDto.getEmail()) == false) {
			throw new BaseException(ExceptionCode.ER0028);
		}
		// save customer email to entity
		customer.setEmail(customerDto.getEmail());

		// check if customer address is null
		if (customerDto.getAddress() == null || customerDto.getAddress().equals("")) {
			throw new BaseException(ExceptionCode.ER0029);
		}
		// save customer address to entity
		customer.setAddress(customerDto.getAddress());

		return customerService.createCustomer(customer);
	}

	@PutMapping("/edit")
	public Customer editCustomer(@RequestBody CustomerDto customerDto) {
		// declare an entity to save data
		Customer customer = new Customer();

		//check if customer id is null
		if(customerDto.getCustomerId() == null) {
			throw new BaseException(ExceptionCode.ER0034);
		}
		//check if customer id is existing
		customer = customerService.findById(customerDto.getCustomerId());
		
		//save customer id to entity
		customer.setCustomerId(customerDto.getCustomerId());
		
		// check if customer name is null
		if (customerDto.getName() == null || customerDto.getName().equals("")) {
			throw new BaseException(ExceptionCode.ER0021);
		}
		//check if customer name is changing
		if(!customerDto.getName().equals(customer.getName())) {
			// check if customer name is existing
			if (customerService.checkCustomerName(customerDto.getName()) == false) {
				throw new BaseException(ExceptionCode.ER0022);
			}
			// save customer name to entity
			customer.setName(customerDto.getName());
		}
		

		// check if customer phone is null
		if (customerDto.getPhone() == null || customerDto.getPhone().equals("")) {
			throw new BaseException(ExceptionCode.ER0023);
		}
		// check if customer phone contains invalid character
		if (ValidateMethod.checkNumericString(customerDto.getPhone()) == false) {
			throw new BaseException(ExceptionCode.ER0024);
		}
		//check if customer phone is changing
		if(!customerDto.getPhone().equals(customer.getPhone())) {
			// check if customer phone is existing
			if (customerService.checkCustomerPhone(customerDto.getPhone()) == false) {
				throw new BaseException(ExceptionCode.ER0025);
			}
			// save customer phone to entity
			customer.setPhone(customerDto.getPhone());
		}
		

		// check if customer email is null
		if (customerDto.getEmail() == null || customerDto.getEmail().equals("")) {
			throw new BaseException(ExceptionCode.ER0026);
		}
		// check if customer email is invalid
		if (ValidateMethod.validateEmail(customerDto.getEmail()) == false) {
			throw new BaseException(ExceptionCode.ER0027);
		}
		//check if customer email is changing
		if(!customerDto.getEmail().equals(customer.getEmail())) {
			// check if customer email is existing
			if (customerService.checkCustomerEmail(customerDto.getEmail()) == false) {
				throw new BaseException(ExceptionCode.ER0028);
			}
			// save customer email to entity
			customer.setEmail(customerDto.getEmail());
		}
		

		// check if customer address is null
		if (customerDto.getAddress() == null || customerDto.getAddress().equals("")) {
			throw new BaseException(ExceptionCode.ER0029);
		}
		// save customer address to entity
		customer.setAddress(customerDto.getAddress());

		return customerService.editCustomer(customer);
	}
}
