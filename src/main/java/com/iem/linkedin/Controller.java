package com.iem.linkedin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.XingRepository;
import model.BirthDate;
import model.Contacts;
import model.PhotoUrls;
import model.XingProfile;
import services.XingService;


@RestController
@Configuration
@EntityScan("model")
public class Controller {


	 @Autowired
	  XingService xingService;
	   
	 
	@RequestMapping(value="/login",method = RequestMethod.GET)
	@ResponseBody
	public String initDb()
	{
		xingService = new XingService();
		xingService.login();
	
		
	return "success";
	}
	
	@RequestMapping(value = "/profiles/{profile_id}", method = RequestMethod.GET)
	public XingProfile profileById(@PathVariable("profile_id")String profileId)
	{
		
		return xingService.findByProfileId(profileId);
	}
	
	
}
