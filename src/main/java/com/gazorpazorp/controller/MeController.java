package com.gazorpazorp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
//	@Autowired
//	ImgurClient imgurClient;
	
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
				.orElseThrow(() -> new Exception("Driver for user does not exist"));
	}
	
	@GetMapping("/internal/drivers/{id}")
	@PreAuthorize("#oauth2.hasScope('customer')")
	public ResponseEntity<Driver> getDriverById(@PathVariable Long id) throws Exception {
		return Optional.ofNullable(driverService.getDriverById(id))
				.map(d -> new ResponseEntity<Driver>(d, HttpStatus.OK))
				.orElseThrow(() -> new Exception("Driver with id '" + id + "' does not exist"));
	}
	
//	@PatchMapping("/accounts/drivers/me")
//	@PreAuthorize("#oauth2.hasScope('driver')")
//	public ResponseEntity updateDriverProfilePic(@RequestBody byte[] profilePic) throws Exception {
//		return new ResponseEntity(driverService.updateProfilePic(profilePic), HttpStatus.OK);
////		return new ResponseEntity(customerService.updateCurrentCustomer(customer), HttpStatus.OK);
//		
//	}
	
	
//	@GetMapping("/test")
//	public ImgurResp test () {
//		System.out.println("We're about to enter the client");
//		ResponseEntity<ImgurResp> resp = imgurClient.response();
//		System.out.println("We got out of the client");
//		return resp.getBody();
//	}
}
