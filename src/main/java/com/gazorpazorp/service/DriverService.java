package com.gazorpazorp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gazorpazorp.model.Customer;
import com.gazorpazorp.model.Driver;
import com.gazorpazorp.repository.DriverRepository;

@Service
public class DriverService {

	@Autowired
	DriverRepository driverRepo;
	
	public Driver getCurrentDriver () {
		return driverRepo.findByUserId(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName()));
	}
	
	public Driver createDriver(Driver driver) {
		return driverRepo.save(driver);
	}
	
	public void deleteDriverByUserId(Long userId) {
		driverRepo.deleteByUserId(userId);
	}
}
