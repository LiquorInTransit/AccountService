package com.gazorpazorp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.gazorpazorp.client.UserClient;
import com.gazorpazorp.model.Account;
import com.gazorpazorp.model.Customer;
import com.gazorpazorp.model.Driver;
import com.gazorpazorp.model.User;
import com.gazorpazorp.model.dto.AccountCreationDto;
import com.gazorpazorp.repository.CustomerRepository;
import com.gazorpazorp.repository.DriverRepository;

@Service
public class AccountService {

	@Autowired
	private UserClient userClient;
	
	@Autowired
	CustomerService customerService;
	@Autowired
	DriverService driverService;
	
	/*Steps:
	 * 1. Check for USER by username (create and/or return USER)
	 * 2. Check for CUSTOMER by user_id
	 * 3. Create and/or return CUSTOMER
	 */
	public Account createAccounts(AccountCreationDto dto) {
		User user = new User(dto.getUsername(), dto.getPassword());
		user = userClient.createUser(user);
		
		Assert.notNull(user, "An unexpected error occurred.");
		Account account = new Account();
		account.setAddress(dto.getAddress());
		account.setFirstName(dto.getFirstName());
		account.setLastName(dto.getLastName());
		account.setUserId(user.getId());
			
		customerService.createCustomer(new Customer(user.getId(), dto.getFirstName(), dto.getLastName(), dto.getAddress()));
		driverService.createDriver(new Driver(user.getId(), dto.getFirstName(), dto.getLastName(), dto.getAddress()));
		return null;
	}
}
