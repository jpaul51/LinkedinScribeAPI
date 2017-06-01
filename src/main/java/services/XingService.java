package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import dao.ContactsRepository;
import dao.XingRepository;
import model.BirthDate;
import model.CareerLevel;
import model.Company;
import model.Contacts;
import model.EducationalBackground;
import model.EmploymentStatus;
import model.FormOfEmployment;
import model.Language;
import model.LanguageSkill;
import model.PhotoUrls;
import model.PrivateAddress;
import model.ProfessionalExperience;
import model.School;
import model.XingDate;
import model.XingProfile;



@Service
public class XingService {
	@Autowired
	   XingRepository xingRepo;
	@Autowired
	ContactsRepository contactsRepo;
	
	
	@PostConstruct
	public boolean init(){
		
		
		XingProfile profile = new XingProfile("1234", "jonas", "paul", "http://google.com", "jonas.paul89@gmail.com", "Jonas Paul");
		profile.setBirthDate(new BirthDate(new Long(11),new Long(1995),new Long(01)));
		profile.setGender("m");
		profile.setHaves("Spring,Android");
		PhotoUrls urls = new PhotoUrls();
		urls.setSize_128x128("http://www.openwebanalytics.com/wp-content/plugins/owa/modules/base/i/browsers/128x128/ie.png");
		profile.setPhotoUrls(urls);
		profile.setPrivateAddress(new PrivateAddress("fax","0602445577","0322114455","01000","France","Ain","Bourg-en-Bresse","rue des nouilles","megamail@losdiablos.evl"	));
		final Company firstCompany = new Company("Computing","30000",new XingDate("1955-01"),"coolTag","LosDiablosCoorp","http://losdiablos.evl",CareerLevel.PROFESSIONAL_EXPERIENCED,"Boss Master",new XingDate("1950-01"),"Evil is our domain",	FormOfEmployment.FULL_TIME_EMPLOYEE,false);
		profile.setProfessionalExperience(new ProfessionalExperience(new ArrayList<Company>(Arrays.asList(firstCompany)),firstCompany, null));
		
		HashMap<Language,LanguageSkill> languages = new HashMap<>();
		languages.put(Language.fr, LanguageSkill.NATIVE);
		languages.put(Language.de, LanguageSkill.GOOD);
		languages.put(Language.en, LanguageSkill.GOOD);
		profile.setLanguages(languages);
		profile.setEmploymentStatus(EmploymentStatus.UNEMPLOYED);
		
		School school = new School( "Computer Science", new XingDate("1930-01"), "third grade", "Evil school", new XingDate("1920-01"), "notes?");
		profile.setEducationalBackground(new EducationalBackground(school,new ArrayList<>(Arrays.asList("diablos qualification")),new ArrayList<>(Arrays.asList(school))));
		profile.setWants("Lot of money");
		
		
		XingProfile profile2 = new XingProfile("12345", "Jean", "Pierre", "http://gmail.com", "jean.pierre@gmail.com", "Jean Pierre");
		profile2.setBirthDate(new BirthDate(new Long(22),new Long(2000),new Long(05)));
		profile2.setGender("m");
		profile2.setHaves("Spring,Android");
		PhotoUrls urls2 = new PhotoUrls();
		urls2.setSize_128x128("https://www.mouserunner.com/images/WindowsIconPreview_128x128.png");
		profile2.setPhotoUrls(urls2);
		
		xingRepo.save(profile);
		xingRepo.save(profile2);
		
		
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

		contactsRepo.save(contacts);
		contactsRepo.save(contacts2);
		xingRepo.save(profile);
		xingRepo.save(profile2);
		
		
		return true;
	}
	

	
	
	public Contacts contactsByProfileId(String profileId)
	{
		return xingRepo.findContactsByProfileId(profileId).getContacts();
	}
	
	public List<XingProfile> profilesByTag()
	{
		return (List<XingProfile>) xingRepo.findAll();
	}
	
	
	public XingProfile findByProfileId(String profileId)
	{
		return xingRepo.findProfileById(profileId);
	}
	
}
