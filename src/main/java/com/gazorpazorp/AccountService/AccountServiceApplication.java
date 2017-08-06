package com.gazorpazorp.AccountService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.util.FileCopyUtils;

@SpringBootApplication(scanBasePackages="com.gazorpazorp")
@EnableJpaRepositories("com.gazorpazorp.repository")
@EntityScan(basePackages="com.gazorpazorp")
//@EnableEurekaClient
//@EnableFeignClients

@EnableOAuth2Client
//@EnableHystrix
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}
	
//	@LoadBalanced
//    @Bean
//    public OAuth2RestTemplate loadBalancedOauth2RestTemplate(
//            OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
//        return new OAuth2RestTemplate(resource, context);
//    }
//    
//    //This essentially allows @PreAuthorize("#oauth2.hasScope('system')") to work
//    @Autowired
//	private ResourceServerProperties sso;
//	@Bean
//	public ResourceServerTokenServices myUserInfoTokenServices() {
//		return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
//	}
	
	
	@EnableResourceServer
	public static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
		@Override
		public void configure(HttpSecurity http) throws Exception {
			http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/**").authenticated();
//				.antMatchers(HttpMethod.GET, "/foo").hasAuthority("FOO_READ")
		}		
		@Override
		public void configure (ResourceServerSecurityConfigurer resources) throws Exception {
//			resources.tokenServices(tokenServices());
			resources.tokenStore(tokenStore);
		}
		
		@Autowired
		TokenStore tokenStore;
		
//		@Bean
//		@Primary
//		public DefaultTokenServices tokenServices() {
//			DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//			defaultTokenServices.setTokenStore(tokenStore());
//			return defaultTokenServices;
//		}
//		
//		@Autowired
//		JwtAccessTokenConverter accessTokenConverter;
//		@Bean
//		TokenStore tokenStore() {
//			return new JwtTokenStore(accessTokenConverter);
//		}
//		
		@Bean
		public JwtAccessTokenConverter accessTokenConverter() {
			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
			Resource resource = new ClassPathResource("public.cert");
			String publicKey = null;
			try {
				publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			System.out.println(publicKey);
			converter.setVerifierKey(publicKey);
			
			return converter;
		}
		
	}
	
	
//	@Configuration
//	public static class JwtConfiguration {
//		@Autowired
//		JwtAccessTokenConverter jwtAccessTokenConverter;
//		
//		@Bean
//		@Qualifier("tokenStore")
//		public TokenStore tokenStore() {
//			return new JwtTokenStore(jwtAccessTokenConverter);
//		}
//		
//		@Bean
//		protected JwtAccessTokenConverter accessTokenConverter() {
//			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//			Resource resource = new ClassPathResource("public.cert");
//			String publicKey = null;
//			try {
//				publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}
//			converter.setVerifierKey(publicKey);
//			return converter;
//		}
//	}
}

