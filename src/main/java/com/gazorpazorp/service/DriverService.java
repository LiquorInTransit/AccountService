package com.gazorpazorp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gazorpazorp.client.UserClient;
import com.gazorpazorp.model.Customer;
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
	
	private final Logger logger = LoggerFactory.getLogger(DriverService.class);
	
	//use the userClietn to get the user, adn put everything to gether into a CustomerMeDto
	public Driver getCurrentDriver () {
		Long id = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
		return driverRepo.findByUserId(id);
	}
	public Driver updateProfilePic(byte[] profilePic) {
		Driver driver = getCurrentDriver();
		if (driver != null) {
			//customer.setProfilePic(profilePic);
			return driverRepo.save(driver);
		}		
		return null;
	}
	
	
	public Driver createDriver(Driver driver) {
		return driverRepo.save(driver);
	}
	
	public void deleteDriverByUserId(Long userId) {
		driverRepo.deleteByUserId(userId);
	}
}
