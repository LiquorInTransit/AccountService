package com.gazorpazorp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gazorpazorp.model.dto.AccountCreationDto;
import com.gazorpazorp.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@PreAuthorize("#oauth2.hasScope('signup')")
	@PostMapping
	public ResponseEntity createAccounts (@RequestBody AccountCreationDto accountDto) throws Exception{
		accountService.createAccounts(accountDto);
		return new ResponseEntity(HttpStatus.OK);						
	}
}
