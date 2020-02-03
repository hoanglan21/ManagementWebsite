package com.fsoft.cme.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.cme.dto.AccountDto;
import com.fsoft.cme.entities.Account;
import com.fsoft.cme.exception.BaseException;
import com.fsoft.cme.exception.ExceptionCode;
import com.fsoft.cme.exception.ValidateMethod;
import com.fsoft.cme.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountRestController {
	@Autowired
	private AccountService service;

	@PostMapping("/add")
	public Account createAccount(@RequestBody AccountDto accountDto) {
		// declare an entity to save data
		Account account = new Account();

		// check if user name is null
		if (accountDto.getUsername() == null || accountDto.getUsername().equals("")) {
			throw new BaseException(ExceptionCode.ER0041);
		}
		//check if user name is invalid
		if(ValidateMethod.validateUsername(accountDto.getUsername()) == false) {
			throw new BaseException(ExceptionCode.ER0047);
		}
		// check if user name is existing
		if (service.checkAccountUsername(accountDto.getUsername()) == false) {
			throw new BaseException(ExceptionCode.ER0042);
		}
		// save user name to entity
		account.setUsername(accountDto.getUsername());

		// check if email is null
		if (accountDto.getEmail() == null || accountDto.getEmail().equals("")) {
			throw new BaseException(ExceptionCode.ER0026);
		}
		// check if email is invalid
		if (ValidateMethod.validateEmail(accountDto.getEmail()) == false) {
			throw new BaseException(ExceptionCode.ER0027);
		}
		// check if email is existing
		if (service.checkAccountEmail(accountDto.getEmail()) == false) {
			throw new BaseException(ExceptionCode.ER0028);
		}
		// save email to entity
		account.setEmail(accountDto.getEmail());

		// check if password is null
		if (accountDto.getPassword() == null || accountDto.getPassword().equals("")) {
			throw new BaseException(ExceptionCode.ER0043);
		}
		// check if password's length is over than 4
		if (accountDto.getPassword().length() < 4) {
			throw new BaseException(ExceptionCode.ER0046);
		}
		// check if re-password is null
		if (accountDto.getRepassword() == null || accountDto.getRepassword().equals("")) {
			throw new BaseException(ExceptionCode.ER0044);
		}
		// check if password is matched
		if (!accountDto.getPassword().equals(accountDto.getRepassword())) {
			throw new BaseException(ExceptionCode.ER0045);
		}
		account.setPassword(accountDto.getPassword());

		return service.createAccount(account);
	}
	
	@PostMapping("/login")
	public Account checkAuthentication(@RequestBody AccountDto accountDto) {
		return service.checkAuthentication(accountDto.getUsername(), accountDto.getPassword());
	}

}
