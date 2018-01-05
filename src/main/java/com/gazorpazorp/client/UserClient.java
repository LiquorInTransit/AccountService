package com.gazorpazorp.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gazorpazorp.client.config.TokenRequestClientConfiguration;
import com.gazorpazorp.model.User;

@FeignClient(name = "uaa-service"/*, configuration = TokenRequestClientConfiguration.class*/)
public interface UserClient {

	@PostMapping(value = "/api/users/", consumes = "application/json")
	public User createUser (User user);
	
//	@DeleteMapping(value = "/api/users/{id}")
//	public boolean deleteUserById(@PathVariable("id") Long id);
	
//	@GetMapping(value ="/api/users/{id}")
//	public User getUserById(@PathVariable("id") Long id);
}
