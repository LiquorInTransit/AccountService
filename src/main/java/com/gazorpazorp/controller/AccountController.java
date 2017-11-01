package com.gazorpazorp.controller;

import java.util.Optional;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gazorpazorp.model.dto.AccountCreationDto;
import com.gazorpazorp.model.dto.CustomerInfoUpdateDto;
import com.gazorpazorp.service.AccountService;
import com.gazorpazorp.service.CustomerService;

@RestController
@RequestMapping("/api/accounts")
@MultipartConfig(fileSizeThreshold = 20971520)
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	CustomerService customerService;
	
	@PreAuthorize("#oauth2.hasScope('signup')")
	@PostMapping
	public ResponseEntity createAccounts (@RequestBody AccountCreationDto accountDto) throws Exception{
		accountService.createAccounts(accountDto);
		return new ResponseEntity(HttpStatus.OK);						
	}
	
	@PatchMapping(value="/me")
	@PreAuthorize("#oauth2.hasScope('customer')")
	public ResponseEntity updateCustomerProfilePic(@RequestBody CustomerInfoUpdateDto dto) throws Exception {
		return Optional.ofNullable(customerService.updateCustomer(dto))
				.map(c -> new ResponseEntity(c, HttpStatus.OK))
				.orElseThrow(() -> new Exception ("Failed to update Customer"));
//		return new ResponseEntity(customerService.updateCurrentCustomer(customer), HttpStatus.OK);
		
	}
}
