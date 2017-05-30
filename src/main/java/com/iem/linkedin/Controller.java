package com.iem.linkedin;

import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.Connection;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
public class Controller {

	static String clientId="78o0ctt9334wxc";
	static String clientSecret="K6nSsWxR68xInens";
	
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	@ResponseBody
	public String initDb()
	{
		LinkedInConnectionFactory connectionFactory = new LinkedInConnectionFactory(clientId, clientSecret);
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		OAuth2Parameters params = new OAuth2Parameters();
		
		params.setRedirectUri("http://localhost:8080/redirect");
		
		params.setState("HGJDPkdksdiKNKJJsdfjflkvg1548");
		String authorizeUrl = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE ,params);
		System.out.println(authorizeUrl);
		
		AccessGrant accessGrant = oauthOperations.authenticateClient(authorizeUrl);
		
		
		//response.sendRedirect(authorizeUrl);
		//AccessGrant accessGrant = new AccessGrant(accessToken);
		
		
		Connection<LinkedIn> connection =  connectionFactory.createConnection(accessGrant);
		return "redirect:/redirect";

	}
	
	@RequestMapping(value="/redirect",method = RequestMethod.GET)
	@ResponseBody
	public String redirect(@RequestParam String code)
	{
		return code;
	}
	
	
}
