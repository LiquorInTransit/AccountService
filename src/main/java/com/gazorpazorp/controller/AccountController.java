package com.gazorpazorp.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gazorpazorp.model.Account;
import com.gazorpazorp.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@PreAuthorize("#oauth2.hasScope('read')")
	@GetMapping("/accounts")
	public ResponseEntity<Account> getCurrentAccount() throws Exception {
		return accountService.getCurrentAccount()
				.map(a -> new ResponseEntity<Account>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Accounts for user do not exist"));
	}
	
	@PreAuthorize("#oauth2.hasScope('system')")
	@GetMapping("/accounts/{id}")
	public ResponseEntity getAccountById(@PathVariable Long id) throws Exception{
		System.out.println("getAccountById: " + id);
		return Optional.ofNullable(accountService.getAccountById(id))
				.map(a -> new ResponseEntity<Account>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Account does not exist"));
	}
	
	@PreAuthorize("#oauth2.hasScope('system')")
	@GetMapping("/accounts/by_user_id/{id}")
	public ResponseEntity<List<Account>> getAccountsByUserId(@PathVariable Long id) throws Exception{
		System.out.println("getAccountsByUserId: " + id);
		return Optional.ofNullable(accountService.getAccountsByUserId(id))
				.map(a -> new ResponseEntity<List<Account>>(a, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Account does not exist"));
	}
	
	@GetMapping("/me")
	public Principal me (Principal principal) {
		return principal;
	}
}
