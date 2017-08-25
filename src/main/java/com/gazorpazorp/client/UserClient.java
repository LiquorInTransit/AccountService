package com.gazorpazorp.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gazorpazorp.client.config.TokenRequestClientConfiguration;
import com.gazorpazorp.model.User;

@FeignClient(name = "uaa-service", configuration = TokenRequestClientConfiguration.class)
public interface UserClient {

	@PostMapping(value = "/uaa/create/", consumes = "application/json")
	public User createUser (User user);
	
	@DeleteMapping(value = "/uaa/delete/{id}")
	public boolean deleteUserById(@PathVariable("id") Long id);
}
