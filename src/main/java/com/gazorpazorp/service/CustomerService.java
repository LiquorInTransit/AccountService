package com.gazorpazorp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gazorpazorp.client.UserClient;
import com.gazorpazorp.model.Customer;
import com.gazorpazorp.model.dto.CustomerDetailsDto;
import com.gazorpazorp.model.dtoMapper.CustomerMapper;
import com.gazorpazorp.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	UserClient userClient;
	
	//use the userClietn to get the user, adn put everything to gether into a CustomerMeDto
	public CustomerDetailsDto getCurrentCustomer () {
		Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
		return CustomerMapper.INSTANCE.customerAndUserToCustomerDetailsDto(customerRepo.findByUserId(id), userClient.getUserById(id));
	}
	
	public Customer createCustomer (Customer customer) {
		return customerRepo.save(customer);
	}	
	public void deleteCustomerByUserId(Long userId) {
		customerRepo.deleteByUserId(userId);
	}
}
