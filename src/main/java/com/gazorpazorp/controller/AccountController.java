package com.gazorpazorp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gazorpazorp.model.Account;
import com.gazorpazorp.model.dto.AccountCreationDto;
import com.gazorpazorp.service.AccountService;

@RestController
@RequestMapping("/api")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@PreAuthorize("#oauth2.hasScope('signup')")
	@PostMapping("/accounts")
	public ResponseEntity createAccounts (@RequestBody AccountCreationDto accountDto) {
		Account account = accountService.createAccounts(accountDto);
		return new ResponseEntity(account, HttpStatus.OK);
	}
}
