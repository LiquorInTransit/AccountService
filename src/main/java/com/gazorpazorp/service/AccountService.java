package com.gazorpazorp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.gazorpazorp.client.UserClient;
import com.gazorpazorp.model.Account;
import com.gazorpazorp.model.User;
import com.gazorpazorp.model.dto.AccountCreationDto;
import com.gazorpazorp.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private UserClient userClient;
//	@Autowired
////	@LoadBalanced
//	OAuth2RestTemplate oAuth2RestTemplate;
	
	public Optional<Account> getCurrentAccount () {
		List<Account> accounts = null;
//		User user = oAuth2RestTemplate.getForObject("http://localhost:5000/uaa/me", User.class);
//		System.out.println(user);
//		if (user != null)
		System.out.println("Received request for id " + SecurityContextHolder.getContext().getAuthentication().getName());
		String clientId = ((OAuth2Authentication)SecurityContextHolder.getContext().getAuthentication()).getOAuth2Request().getClientId();
//		System.out.println(clientId);
		accounts = accountRepository.findAccountsByUserId(Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName()));
		
		return accounts.stream().filter(account -> ("LITMobileCustomerClient".equals(clientId))?(!account.isDriver()):account.isDriver()).findFirst();
	}
	
	public Account getAccountById(Long id) {
		return accountRepository.findAccountById(id);
	}
	
	public List<Account> getAccountsByUserId(Long id) {
		return accountRepository.findAccountsByUserId(id);
	}
	
	
	public Account createAccount(AccountCreationDto dto) {
		//Make sure an account with this name doesn't already exist
		Account existing = accountRepository.findByFirstName(dto.getFirstName());//TODO: Change this check to EMAIL
		Assert.isNull(existing, "Account with that email already exists.");
		
		User user = new User(dto.getUsername(), dto.getPassword());
		System.out.println(user);
		user = userClient.createUser(user);
		
		Assert.notNull(user, "An unexpected error occurred.");
		
		Account account = new Account();
		account.setAddress(dto.getAddress());
		account.setFirstName(dto.getFirstName());
		account.setLastName(dto.getLastName());
		account.setUserId(user.getId());
		
		account = accountRepository.save(account);
		
		return account;
	}
}
