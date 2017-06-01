package com.iem.linkedin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import controller.Controller;
import dao.XingRepository;
import model.XingProfile;
import services.XingService;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("model")
@ComponentScan(basePackageClasses =Controller.class)
@ComponentScan({"controller","dao","model","srvices"})
@ComponentScan(basePackageClasses = { XingService.class,XingRepository.class})
@EnableJpaRepositories("dao")
public class DemoApplication {
 
	static String clientId="78o0ctt9334wxc";
	static String clientSecret="K6nSsWxR68xInens";
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
		
	}
}









