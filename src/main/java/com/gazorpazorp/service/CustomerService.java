package com.gazorpazorp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gazorpazorp.client.UserClient;
import com.gazorpazorp.model.Customer;
import com.gazorpazorp.model.Driver;
import com.gazorpazorp.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	UserClient userClient;
	
	private final Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	//use the userClietn to get the user, adn put everything to gether into a CustomerMeDto
	public Customer getCurrentCustomer () {
		Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
		return customerRepo.findByUserId(id);
	}
	
	public Customer createCustomer (Customer customer) {
		return customerRepo.save(customer);
	}	
	public void deleteCustomerByUserId(Long userId) {
		customerRepo.deleteByUserId(userId);
	}
	
	public Customer updateProfilePic(byte[] profilePic) {
		Customer customer = getCurrentCustomer();
		if (customer != null) {
			//customer.setProfilePic(profilePic);
			return customerRepo.save(customer);
		}		
		return null;
	}
}
