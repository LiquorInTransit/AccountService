package com.gazorpazorp.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gazorpazorp.client.config.TokenRequestClientConfiguration;
import com.gazorpazorp.model.Account;
import com.gazorpazorp.model.User;

@FeignClient(name = "customer-client", configuration = TokenRequestClientConfiguration.class)
public interface CustomerClient {

	@PostMapping(value = "/customers/", consumes = "application/json")
	public User createCustomer (Account account);
	
	@DeleteMapping(value = "/customers/{userId}")
	public boolean deleteCustomerByUserId(@PathVariable("userId") Long userId);
}
