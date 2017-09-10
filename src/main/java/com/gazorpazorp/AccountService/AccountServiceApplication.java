package com.gazorpazorp.AccountService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication(scanBasePackages="com.gazorpazorp")
@EnableJpaRepositories("com.gazorpazorp.repository")
@EntityScan(basePackages="com.gazorpazorp")
@EnableEurekaClient
@EnableFeignClients("com.gazorpazorp.client")
@EnableResourceServer
@EnableOAuth2Client
//@EnableHystrix
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}
	
//	@PostConstruct
//	public void getDbManager(){
//	   DatabaseManagerSwing.main(
//		new String[] { "--url", "jdbc:hsqldb:mem:test1://localhost/test1", "--user", "SA", "--password", ""});
//	}
}

