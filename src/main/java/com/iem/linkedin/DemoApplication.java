package com.iem.linkedin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.config.annotation.EnableSocial;

@SpringBootApplication



public class DemoApplication {

	static String clientId="78o0ctt9334wxc";
	static String clientSecret="K6nSsWxR68xInens";
	
	@Configuration
	public class SocialConfig {
		
	  

	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
		
	}
}









