package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import dao.XingRepository;
import model.BirthDate;
import model.Contacts;
import model.PhotoUrls;
import model.XingProfile;

@Service
@EnableJpaRepositories("dao")
public class XingService {
	@Autowired
	   XingRepository xingRepo;
	
	public boolean login(){
		
		
		XingProfile profile = new XingProfile("1234", "jonas", "paul", "http://google.com", "jonas.paul89@gmail.com", "Jonas Paul");
		profile.setBirthDate(new BirthDate(new Long(11),new Long(1995),new Long(01)));
		profile.setGender("m");
		profile.setHaves("Spring,Android");
		PhotoUrls urls = new PhotoUrls();
		urls.setSize_1024x1024("http://www.mrwallpaper.com/wallpapers/Color-Pencils-1024x1024.jpg");
		profile.setPhotoUrls(urls);
		
		XingProfile profile2 = new XingProfile("12345", "Jean", "Pierre", "http://gmail.com", "jean.pierre@gmail.com", "Jean Pierre");
		profile2.setBirthDate(new BirthDate(new Long(22),new Long(2000),new Long(05)));
		profile2.setGender("m");
		profile2.setHaves("Spring,Android");
		PhotoUrls urls2 = new PhotoUrls();
		urls2.setSize_1024x1024("http://wfiles.brothersoft.com/s/superman-logo_6968-1024x1024.jpg");
		profile2.setPhotoUrls(urls2);

		Contacts contacts = new Contacts();
		List<XingProfile> contactProfiles = new ArrayList<>();
		contactProfiles.add(profile2);
		contacts.setUsers(contactProfiles);
		profile.setContacts(contacts);
		
		Contacts contacts2 = new Contacts();
		List<XingProfile> contactProfiles2 = new ArrayList<>();
		contactProfiles2.add(profile);
		contacts2.setUsers(contactProfiles2);
		profile2.setContacts(contacts2);

		xingRepo.save(profile);
		xingRepo.save(profile2);
		return true;
	}
	
	public XingProfile findByProfileId(String profileId)
	{
		return xingRepo.findProfileById(profileId);
	}
	
}
