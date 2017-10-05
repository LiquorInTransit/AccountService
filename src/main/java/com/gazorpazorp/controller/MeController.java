package com.gazorpazorp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gazorpazorp.model.Customer;
import com.gazorpazorp.model.Driver;
import com.gazorpazorp.service.CustomerService;
import com.gazorpazorp.service.DriverService;

@RestController
public class MeController {
	
	@Autowired
	CustomerService customerService;
	@Autowired
	DriverService driverService;
	
	@GetMapping("/me")
	@PreAuthorize("#oauth2.hasScope('customer')")
	public ResponseEntity<Customer> getCurrentCustomer() throws Exception {
		return Optional.ofNullable(customerService.getCurrentCustomer())
				.map(c -> new ResponseEntity<Customer>(c, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Customer for user does not exist"));
	}
	
	
	
	
	@GetMapping("/drivers/me")
	@PreAuthorize("#oauth2.hasScope('driver')")
	public ResponseEntity<Driver> getCurrentDriver() throws Exception {
		return Optional.ofNullable(driverService.getCurrentDriver())
				.map(d -> new ResponseEntity<Driver>(d, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Customer for user does not exist"));
	}
	
	@PatchMapping("/accounts/drivers/me")
	@PreAuthorize("#oauth2.hasScope('driver')")
	public ResponseEntity updateDriverProfilePic(@RequestBody byte[] profilePic) throws Exception {
		return new ResponseEntity(driverService.updateProfilePic(profilePic), HttpStatus.OK);
//		return new ResponseEntity(customerService.updateCurrentCustomer(customer), HttpStatus.OK);
		
	}
}
