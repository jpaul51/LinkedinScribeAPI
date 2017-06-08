package controller;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.internet.InternetAddress;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.sun.mail.iap.ByteArray;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.EmailAttachment;
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

	@RequestMapping(value="/favoris/folder/remove",method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,String> removeFavorisFolder(@RequestParam String name, @RequestParam String password, @RequestParam("title") String title )
	{
		
		Admin admin = adminService.login(name, password);
		
		Favoris favToDelete = null;
		for(Favoris fav : admin.getFav())
		{
			if(title.equals(fav.getTitle()))
			{
				favToDelete = fav;
			}
		}
		
		adminService.removeFavorisFolder(admin, favToDelete);
		//return adminService.removeFavoris(profileToEdit, admin, title);
		return null;

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

	@RequestMapping(value="favoris/export", method = RequestMethod.POST)
	public HashMap<String,String> sendMail(@RequestParam String name, @RequestParam String password){

		Admin admin = adminService.login(name, password);
		HashMap<String,String> res = new HashMap<>();
		if(admin != null)
		{
			sendEmailWithoutTemplating(admin);
			res.put("code", "ok");
			res.put("message", "Mail sent");
		}
		else
		{
			res.put("code", "KO");
			res.put("message", "login failed");
		}

		return res;
	}



	@Autowired
	public EmailService emailService;

	public void sendEmailWithoutTemplating(Admin admin){


		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Datatypes in Java");

		XSSFRow header = sheet.createRow(0);
		header.createCell(0).setCellValue("List name");
		header.createCell(1).setCellValue("User name");
		header.createCell(2).setCellValue("mail");
		header.createCell(3).setCellValue("haves");
		header.createCell(4).setCellValue("wants");
		header.createCell(5).setCellValue("comments");


		int rowCount = 1;
		for(Favoris fav : admin.getFav())
		{
			Row favRow = sheet.createRow(rowCount++);
			favRow.createCell(0).setCellValue(fav.getTitle());
			for(XingProfile profile : fav.getFavList())
			{
				Row profileRow = sheet.createRow(rowCount++);
				profileRow.createCell(1).setCellValue(profile.getDisplayName());
				profileRow.createCell(2).setCellValue(profile.getActiveEmail());
				profileRow.createCell(3).setCellValue(profile.getHaves());
				profileRow.createCell(4).setCellValue(profile.getWants());
				String commentCellValue = "";
				for(Comments comments : profile.getComments())
				{
					for(Comment comment : comments.getCommentList())
					{

						commentCellValue+=comment.getComment()+"\n";
					}
				}
				profileRow.createCell(5).setCellValue(commentCellValue);
				sheet.autoSizeColumn(rowCount);

			}
			sheet.autoSizeColumn(rowCount);
		}

		FileOutputStream outputStream;

		try {

			outputStream = new FileOutputStream("tmp/favExport.xls");
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Done");




		Email email = null;
		try {

			email = DefaultEmail.builder()
					.from(new InternetAddress("contact@jonas-paul.me", "Xing API Support"))
					.to(Lists.newArrayList(new InternetAddress(admin.getName(), admin.getName())))
					.subject("Your favorites Xing profiles")
					.body("You will find your favorites Xing profile in attachement")
					.attachment(new EmailAttachment() {

						@Override
						public MediaType getContentType() throws IOException {
							// TODO Auto-generated method stub
							return MediaType.APPLICATION_OCTET_STREAM;
						}

						@Override
						public String getAttachmentName() {
							// TODO Auto-generated method stub
							return "favExport.xls";
						}

						@Override
						public byte[] getAttachmentData() {
							// TODO Auto-generated method stub
							Path path =FileSystems.getDefault().getPath("tmp/favExport.xls");
							byte[] bytes = null;
							try {
								bytes = Files.readAllBytes(path);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return bytes;
						}
					})
					.encoding("UTF-8").build();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		emailService.send(email);
	}





}
