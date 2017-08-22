package com.gazorpazorp.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gazorpazorp.client.config.TokenRequestClientConfiguration;
import com.gazorpazorp.model.Account;
import com.gazorpazorp.model.User;

@FeignClient(name = "driver-client", configuration = TokenRequestClientConfiguration.class)
public interface DriverClient {

	@PostMapping(value = "/drivers/", consumes = "application/json")
	public User createDriver (Account account);
	
	@DeleteMapping(value = "/drivers/{userId}")
	public boolean deleteDriverByUserId(@PathVariable("userId") Long userId);
}
