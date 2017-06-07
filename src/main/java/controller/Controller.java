package controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.internet.InternetAddress;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import model.Admin;
import model.Comment;
import model.Comments;
import model.Contacts;
import model.Favoris;
import model.XingProfile;
import services.AdminService;
import services.CommentService;
import services.XingService;

@RestController
@Configuration 
@EnableJpaRepositories("dao")
@EntityScan("model")

public class Controller {


	@Autowired
	XingService xingService;

	@Autowired
	AdminService adminService;
	@Autowired 
	CommentService commentService;

	@RequestMapping(value="/login",method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,String> login(@RequestParam String name,@RequestParam String password )
	{
		Admin admin = adminService.login(name,password);

		if(admin==null)
		{
			if(!adminService.adminExists(name))
			{
				admin = new Admin(name, password);
				adminService.addAdmin(admin);
			}
		}

		HashMap<String,String> response = new HashMap<>();
		response.put("code", "ok");
		return response;
	}



	@RequestMapping(value="/favoris/get",method = RequestMethod.POST)
	@ResponseBody
	public List<Favoris> getFavoris(@RequestParam String name, @RequestParam String password )
	{
		Admin loggedAdmin = adminService.login(name, password);
		if(loggedAdmin!=null)
		{
			return loggedAdmin.getFav();
		}

		return new ArrayList<>();

	}
	
	
	@RequestMapping(value="/favoris/remove/",method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,String> removeFavoris(@RequestParam String name, @RequestParam String password, @RequestParam("title") String title, @RequestParam("profile_id") String profileId )
	{
		XingProfile profileToEdit = xingService.findByProfileId(profileId);
		System.out.println(name+", "+password);
		Admin admin = adminService.login(name, password);
		System.out.println(admin.getFav().size());
		return adminService.removeFavoris(profileToEdit, admin, title);
		
		
	}
	

	@RequestMapping(value="/favoris/add",method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,String> addFavoris(@RequestParam String name, @RequestParam String password, @RequestParam String title, @RequestParam("profile_id_list") List<String> profileIdList )
	{

		Admin loggedAdmin = adminService.login(name, password);

		Favoris favToAdd=null;

		if(loggedAdmin != null)
		{
			List<Favoris> favMap = loggedAdmin.getFav();

			if(favMap != null)
			{

				System.out.println("SIZE: "+favMap.size());

				if(favMap.contains(new Favoris(title)))
				{
					favToAdd = favMap.get(favMap.indexOf(new Favoris(title)));
					System.out.println("Editing: "+favToAdd.getTitle()+", SIZE: "+favToAdd.getFavList().size()+", ID: "+favToAdd.getId());
					List<XingProfile> profiles = favToAdd.getFavList();

					if(profiles == null)
						profiles = new ArrayList<>();

					List<XingProfile> xingProfilesToAdd = new ArrayList<>();

					for(String profileIdToAdd : profileIdList)
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
					for(String profileId : profileIdList)
					{
						XingProfile profile = xingService.findByProfileId(profileId);
						if(profile != null)
						{

							profiles.add(profile);
						}

					}
					favToAdd = new Favoris(title, profiles);
					System.out.println("ADD fav: "+favToAdd.toString());
					favMap.add(favToAdd);
					loggedAdmin.setFav(favMap);
				}
			}
			else
			{
				favMap = new ArrayList();

				ArrayList<XingProfile> profiles = new ArrayList<>();
				for(String profileId : profileIdList)
				{
					XingProfile profile = xingService.findByProfileId(profileId);
					if(profile != null)
					{
						profiles.add(profile);
					}

				}

				favToAdd = new Favoris(title, profiles);
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
		System.out.println(tag);
		return xingService.profilesByTag(tag);
	}


	
	
	
	@RequestMapping(value="/profiles/comments/add", method = RequestMethod.POST)
	public HashMap<String,String> addComment(@RequestParam("profile_id") String profileId, @RequestParam String comment, @RequestParam String name, @RequestParam String password)
	{
		Admin admin = adminService.login(name, password);
		HashMap<String,String> returnValue = new HashMap<>();
		returnValue.put("code", "ko");
		if(admin != null)
		{
			XingProfile profile = xingService.findByProfileId(profileId);
			if(profile !=null)
			{
				//System.out.println(profile.getComments().size());
				Comments commentsObject = profile.findCommentsObjectByAdminName(name);
				Comment commentToAdd = new Comment(comment,DateTime.now());
				commentsObject.getCommentList().add(commentToAdd);
				
				profile.getComments().add(commentsObject);
				
				commentService.updateComment(commentToAdd);
				commentService.updateComments(commentsObject);
				xingService.updateProfile(profile);
				
				returnValue.put("code", "ok");
				returnValue.put("message", "Comment added to profile "+profile.getId());
				
//				System.out.println(profile.getComments().size());
//				System.out.println(profile.getComments().iterator().next().getCommentList().size());
				
				
			}
			else
			{
				returnValue.put("message", "profile "+profileId+" does not exist");
			}
		}
		else
		{
			returnValue.put("message", "login failed");
		}
		return returnValue;
		
	}
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public void sendMail(){
		sendEmailWithoutTemplating();
		
	}
	
	
	
	@Autowired
	public EmailService emailService;

	public void sendEmailWithoutTemplating(){
	   Email email = null;
	try {
		email = DefaultEmail.builder()
		        .from(new InternetAddress("contact@jonas-paul.me", "Xing API Support"))
		        .to(Lists.newArrayList(new InternetAddress("jonas.paul89@gmail.com", "Jonas Paul")))
		        .subject("Laelius de amicitia")
		        .body("Firmamentum autem stabilitatis constantiaeque eius, quam in amicitia quaerimus, fides est.")
		        .encoding("UTF-8").build();
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	   emailService.send(email);
	}
	

}
