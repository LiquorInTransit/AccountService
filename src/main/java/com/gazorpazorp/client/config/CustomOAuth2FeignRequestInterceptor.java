package com.gazorpazorp.client.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import feign.RequestInterceptor;
import feign.RequestTemplate;
//via https://gist.github.com/joaoevangelista/dc90bcea15da5f554c7c
public class CustomOAuth2FeignRequestInterceptor implements RequestInterceptor{
	
	private final OAuth2ClientContext oAuth2ClientContext;
    private final String tokenTypeName;
    private final String headerName;
    private final Logger logger = LoggerFactory.getLogger(CustomOAuth2FeignRequestInterceptor.class);


    public CustomOAuth2FeignRequestInterceptor(OAuth2ClientContext oAuth2ClientContext) {
        this(oAuth2ClientContext, "Bearer", "Authorization");
    }
    

    public CustomOAuth2FeignRequestInterceptor(OAuth2ClientContext oAuth2ClientContext, String tokenTypeName, String headerName) {
        this.oAuth2ClientContext = oAuth2ClientContext;
        this.tokenTypeName = tokenTypeName;
        this.headerName = headerName;
    }

    @Override
    public void apply(RequestTemplate template) {
    	String tokenString = ((OAuth2AuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getDetails()).getTokenValue();
        if ( /*oAuth2ClientContext.getAccessToken()*/tokenString == null) {
            logger.warn("Cannot obtain existing token for request, if it is a non secured request, ignore.");
        } else {
            logger.debug("Constructing Header {} for Token {}", headerName, tokenTypeName);
            template.header(headerName, String.format("%s %s", tokenTypeName, tokenString/*oAuth2ClientContext.getAccessToken().toString()*/));
        }

    }

}
