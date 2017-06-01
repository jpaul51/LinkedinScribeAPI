package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import model.Admin;
import model.Contacts;
import model.Favoris;
import model.XingProfile;
import services.AdminService;
import services.XingService;
import web.FavorisBody;
import web.LoginBody;

@RestController
@Configuration 
@EnableJpaRepositories("dao")
@EntityScan("model")

public class Controller {


	 @Autowired
	  XingService xingService;
	   
	 @Autowired
	 AdminService adminService;
	 
	@RequestMapping(value="/login",method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,String> login(@RequestBody LoginBody body )
	{
		Admin admin = adminService.login(body.name,body.password);
		
		if(admin==null)
		{
			admin = new Admin(body.name, body.password);
			adminService.addAdmin(admin);
		}
		
		HashMap<String,String> response = new HashMap<>();
		response.put("code", "ok");
	return response;
	}
	
	
	
	@RequestMapping(value="/favoris/get",method = RequestMethod.POST)
	@ResponseBody
	public List<Favoris> getFavoris(@RequestBody LoginBody body )
	{
		Admin loggedAdmin = adminService.login(body.name, body.password);
		if(loggedAdmin!=null)
		{
			return loggedAdmin.getFav();
		}
		
		return new ArrayList<>();
		
	}
	
	@RequestMapping(value="/favoris/add",method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,String> addFavoris(@RequestBody FavorisBody body )
	{
		
		Admin loggedAdmin = adminService.login(body.name, body.password);
		
		Favoris favToAdd=null;
		
		if(loggedAdmin != null)
		{
			List<Favoris> favMap = loggedAdmin.getFav();
			
			if(favMap != null)
			{
				
				System.out.println("SIZE: "+favMap.size());
				
				if(favMap.contains(new Favoris(body.title)))
				{
					favToAdd = favMap.get(favMap.indexOf(new Favoris(body.title)));
					System.out.println("Editing: "+favToAdd.getTitle()+", SIZE: "+favToAdd.getFavList().size()+", ID: "+favToAdd.getId());
					List<XingProfile> profiles = favToAdd.getFavList();
					
					if(profiles == null)
						profiles = new ArrayList<>();
					
					List<XingProfile> xingProfilesToAdd = new ArrayList<>();
					
					for(String profileIdToAdd : body.profileId)
					{
						XingProfile xingProfileToAdd = xingService.findByProfileId(profileIdToAdd);
						if(xingProfileToAdd!=null)
							xingProfilesToAdd.add(xingProfileToAdd);
					}
					
					
					
					profiles.addAll(xingProfilesToAdd);
					
					Set<XingProfile> profilesNoDuplicate = new HashSet<>();
					profilesNoDuplicate.addAll(profiles);
					
					
					favToAdd.setFavList(new ArrayList<XingProfile>(profilesNoDuplicate));
					
				}
				else
				{
					
					ArrayList<XingProfile> profiles = new ArrayList<>();
					for(String profileId : body.profileId)
					{
						XingProfile profile = xingService.findByProfileId(profileId);
						if(profile != null)
						{
							
							profiles.add(profile);
						}
						
					}
					favToAdd = new Favoris(body.title, profiles);
					System.out.println("ADD fav: "+favToAdd.toString());
					favMap.add(favToAdd);
					loggedAdmin.setFav(favMap);
				}
			}
			else
			{
				favMap = new ArrayList();
				
				ArrayList<XingProfile> profiles = new ArrayList<>();
				for(String profileId : body.profileId)
				{
					XingProfile profile = xingService.findByProfileId(profileId);
					if(profile != null)
					{
						profiles.add(profile);
					}
					
				}
				
				favToAdd = new Favoris(body.title, profiles);
				favMap.add(favToAdd);
				loggedAdmin.setFav(favMap);
			}
			
			adminService.addAdmin(favToAdd,loggedAdmin);
			
			
			
			
		}
		
		HashMap<String,String> response = new HashMap<>();
		response.put("code", "ok");
	return response;
	}
	
	
	
	
	
	@RequestMapping(value = "/profiles/{profile_id}", method = RequestMethod.GET)
	public XingProfile profileById(@PathVariable("profile_id")String profileId)
	{
		
		return xingService.findByProfileId(profileId);
	}
	
	@RequestMapping(value="/profiles/{profile_id}/contacts")
	public Contacts contactsByProfileId(@PathVariable("profile_id") String profileId)
	{
		return xingService.contactsByProfileId(profileId);
	}
	
	@RequestMapping(value="/profiles/tag/{profiles_tag}")
	public List<XingProfile> profilesByTag(@PathVariable("profiles_tag")String tag)
	{
		return xingService.profilesByTag();
	}
	
	
	
}