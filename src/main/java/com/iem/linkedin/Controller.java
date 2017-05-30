package com.iem.linkedin;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.scribejava.apis.LinkedInApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
@RestController
@Configuration
public class Controller {

	static String clientId="78o0ctt9334wxc";
	static String clientSecret="K6nSsWxR68xInens";
	 private static final String PROTECTED_RESOURCE_URL
     = "https://api.linkedin.com/v1/people/~?format=json";
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	@ResponseBody
	public String initDb()
	{
		 final OAuth10aService service = new ServiceBuilder()
	                .apiKey(clientId)
	                .apiSecret(clientSecret)
	                .build(LinkedInApi.instance());
	        final Scanner in = new Scanner(System.in);

	        System.out.println("=== LinkedIn's OAuth Workflow ===");
	        System.out.println();

	        // Obtain the Request Token
	        System.out.println("Fetching the Request Token...");
	        OAuth1RequestToken requestToken=null;
	        
			try {
				requestToken = service.getRequestToken();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println("Got the Request Token!");
	        System.out.println();

	        System.out.println("Now go and authorize ScribeJava here:");
	        System.out.println(service.getAuthorizationUrl(requestToken));
	        System.out.println("And paste the verifier here");
	        System.out.print(">>");
	        final String oauthVerifier = in.nextLine();
	        System.out.println();

	        // Trade the Request Token and Verfier for the Access Token
	        System.out.println("Trading the Request Token for an Access Token...");
	        OAuth1AccessToken accessToken=null;
			try {
				accessToken = service.getAccessToken(requestToken, oauthVerifier);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println("Got the Access Token!");
	        System.out.println("(if your curious it looks like this: " + accessToken
	                + ", 'rawResponse'='" + accessToken.getRawResponse() + "')");
	        System.out.println();

	        // Now let's go and ask for a protected resource!
	        System.out.println("Now we're going to access a protected resource...");
	        final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
	        service.signRequest(accessToken, request);
	        Response response=null;
			try {
				response = service.execute(request);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        System.out.println("Got it! Lets see what we found...");
	        System.out.println();
	        try {
				System.out.println(response.getBody());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        System.out.println();
	        System.out.println("Thats it man! Go and build something awesome with ScribeJava! :)");
	        return "success";
	}
	
	@RequestMapping(value="/redirect",method = RequestMethod.GET)
	@ResponseBody
	public String redirect()
	{
		return "hello";
	}
	
	
}
