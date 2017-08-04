package com.gazorpazorp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.gazorpazorp.model.Account;
import com.gazorpazorp.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	@Autowired
//	@LoadBalanced
	OAuth2RestTemplate oAuth2RestTemplate;
	
	public List<Account> getCurrentAccount () {
		List<Account> account = null;
		User user = oAuth2RestTemplate.getForObject("http://localhost:5000/uaa/me", User.class);
		System.out.println(user);
		if (user != null)
			account = accountRepository.findAccountsByUserId(user.getId());
		
		return account;
	}
	
	public Account getAccountById(Long id) {
		return accountRepository.findAccountById(id);
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
