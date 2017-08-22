package com.gazorpazorp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.gazorpazorp.client.CustomerClient;
import com.gazorpazorp.client.DriverClient;
import com.gazorpazorp.client.UserClient;
import com.gazorpazorp.model.Account;
import com.gazorpazorp.model.User;
import com.gazorpazorp.model.dto.AccountCreationDto;

@Service
public class AccountService {

//	@Autowired
//	private AccountRepository accountRepository;
	@Autowired
	private UserClient userClient;
	@Autowired
	private CustomerClient customerClient;
	@Autowired
	private DriverClient driverClient;
//	@Autowired
////	@LoadBalanced
//	OAuth2RestTemplate oAuth2RestTemplate;
	
//	public Optional<Account> getCurrentAccount () {
//		List<Account> accounts = null;
////		User user = oAuth2RestTemplate.getForObject("http://localhost:5000/uaa/me", User.class);
////		System.out.println(user);
////		if (user != null)
//		String clientId = ((OAuth2Authentication)SecurityContextHolder.getContext().getAuthentication()).getOAuth2Request().getClientId();
////		System.out.println(clientId);
//		accounts = accountRepository.findAccountsByUserId(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName()));
//		
//		return accounts.stream().filter(account -> ("LITMobileCustomerClient".equals(clientId))?(!account.isDriver()):account.isDriver()).findFirst();
//	}
	
//	public Account getAccountById(Long id) {
//		return accountRepository.findAccountById(id);
//	}
//	
//	public List<Account> getAccountsByUserId(Long id) {
//		return accountRepository.findAccountsByUserId(id);
//	}
	
	/*Steps:
	 * 1. Check for USER by username (create and/or return USER)
	 * 2. Check for CUSTOMER by user_id
	 * 3. Create and/or return CUSTOMER
	 */
	public Account createAccounts(AccountCreationDto dto) {
		//Checking for existing account is unnecessary
		//Make sure an account with this name doesn't already exist
//		Account existing = accountRepository.findByFirstName(dto.getFirstName());//TODO: Change this check to EMAIL
//		Assert.isNull(existing, "Account with that email already exists.");
		
		User user = new User(dto.getUsername(), dto.getPassword());
		user = userClient.createUser(user);
		
		Assert.notNull(user, "An unexpected error occurred.");
		Account account = new Account();
		account.setAddress(dto.getAddress());
		account.setFirstName(dto.getFirstName());
		account.setLastName(dto.getLastName());
		account.setUserId(user.getId());
		try {			
			customerClient.createCustomer(account);
			driverClient.createDriver(account);
		} catch (Exception e) {
			deleteAccountsByUserId(user.getId());
		}
//		account = accountRepository.save(account);
		
		return null;
	}
	
	private boolean deleteAccountsByUserId (Long id) {
		try {
			userClient.deleteUserById(id);
			customerClient.deleteCustomerByUserId(id);
			driverClient.deleteDriverByUserId(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
