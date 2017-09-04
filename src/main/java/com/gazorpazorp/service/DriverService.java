package com.gazorpazorp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gazorpazorp.client.UserClient;
import com.gazorpazorp.model.Driver;
import com.gazorpazorp.model.dto.DriverDetailsDto;
import com.gazorpazorp.model.dtoMapper.DriverMapper;
import com.gazorpazorp.repository.DriverRepository;

@Service
public class DriverService {

	@Autowired
	DriverRepository driverRepo;
	@Autowired
	UserClient userClient;
	
	//use the userClietn to get the user, adn put everything to gether into a CustomerMeDto
	public DriverDetailsDto getCurrentDriver () {
		
		Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
		return DriverMapper.INSTANCE.customerAndUserToCustomerDetailsDto(driverRepo.findByUserId(id), userClient.getUserById(id));
	}
	
	public Driver createDriver(Driver driver) {
		return driverRepo.save(driver);
	}
	
	public void deleteDriverByUserId(Long userId) {
		driverRepo.deleteByUserId(userId);
	}
}
