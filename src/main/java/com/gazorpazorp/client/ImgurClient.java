package com.gazorpazorp.client;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.gazorpazorp.client.config.ImgurTokenRequestPasswordConfiguration;
import com.gazorpazorp.model.imgur.ImgurResp;

@FeignClient(name="imgur-client", url="https://api.imgur.com/3", configuration = ImgurTokenRequestPasswordConfiguration.class)
public interface ImgurClient {
	
	@GetMapping("/image/GGLM4P8")
	public ResponseEntity<ImgurResp> response();
}
