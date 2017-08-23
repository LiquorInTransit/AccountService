package com.gazorpazorp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import com.gazorpazorp.model.Customer;
import com.gazorpazorp.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepo;
	
	public Customer getCurrentCustomer () {
		return customerRepo.findByUserId(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName()));
	}
	
	public Customer createCustomer (Customer customer) {
		return customerRepo.save(customer);
	}	
	public void deleteCustomerByUserId(Long userId) {
		customerRepo.deleteByUserId(userId);
	}
}
