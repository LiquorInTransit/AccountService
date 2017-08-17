package com.gazorpazorp.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.gazorpazorp.client.config.TokenRequestClientConfiguration;
import com.gazorpazorp.model.User;

@FeignClient(name = "user-client", configuration = TokenRequestClientConfiguration.class)
public interface UserClient {

	@PostMapping(value = "/uaa/create/", consumes = "application/json")
	public User createUser (User user);
}
