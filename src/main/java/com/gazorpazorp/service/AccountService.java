package com.gazorpazorp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import com.gazorpazorp.model.Account;
import com.gazorpazorp.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
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
	
	public static class User {
		private Long id;
		
		public Long getId() {
			return id;
		}

		@Override
		public String toString() {
			return "User [id=" + id + "]";
		}
		
	}
	
}
