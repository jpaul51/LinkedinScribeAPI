package com.iem.linkedin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;

@SpringBootApplication
@EnableSocial
public class DemoApplication {

	static String clientId="78o0ctt9334wxc";
	static String clientSecret="K6nSsWxR68xInens";
	
	@Configuration
	public class SocialConfig {
		
	    @Bean
	    public ConnectionFactoryLocator connectionFactoryLocator() {
	        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
	        registry.addConnectionFactory(new LinkedInConnectionFactory(
	           "78o0ctt9334wxc",
	            "K6nSsWxR68xInens"));
	        
	        return registry;
	    }

	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
		
	}
}









