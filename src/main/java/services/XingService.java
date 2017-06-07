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
		
		XingProfile profile = xingRepo.findProfileById("1234");
		
		if(profile == null)
		{
		XingProfile profile5 = new XingProfile("12345678", "Maxime", "Ducoroy", "http://gmail.com", "maxime.ducoroy@gmail.com", "Maxime Ducoroy");
		profile5.setBirthDate(new BirthDate(new Long(25),new Long(1996),new Long(12)));
		profile5.setGender("m");
		profile5.setHaves("C++,Android,Swift,PHP,.NET");
		PhotoUrls urls5 = new PhotoUrls();
		urls5.setSize_128x128("https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAAmJAAAAJDkxZTU1ODcwLTA1OWMtNDlmZC1hOTg2LTMxOTcyNjBkNmUxYQ.jpg");
		profile5.setPhotoUrls(urls5);

		XingProfile profile4 = new XingProfile("1234567", "Benjamin", "Saugues", "http://gmail.com", "benjamin.saugues@gmail.com", "Benjamin Saugues");
		profile4.setBirthDate(new BirthDate(new Long(20),new Long(1996),new Long(8)));
		profile4.setGender("m");
		profile4.setHaves("HTML5,Android,Swift,PHP,.NET");
		PhotoUrls urls4 = new PhotoUrls();
		urls4.setSize_128x128("https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAA2oAAAAJDE1ZWJlZjhjLTNmZWItNDFkZC05NTBmLTM1YTYzMWNlNjRkZA.jpg");
		profile4.setPhotoUrls(urls4);
		
		
		
		
		 profile = new XingProfile("1234", "jonas", "paul", "http://google.com", "jonas.paul89@gmail.com", "Jonas Paul");
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
		
		
		XingProfile profile3 = new XingProfile("123456", "Mathis", "Laurent", "http://gmail.com", "mathis.laurent@gmail.com", "Mathis Laurent");
		profile3.setBirthDate(new BirthDate(new Long(22),new Long(1996),new Long(05)));
		profile3.setGender("m");
		profile3.setHaves("HTML5,Android,Swift,PHP");
		PhotoUrls urls3 = new PhotoUrls();
		urls3.setSize_128x128("https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAAirAAAAJDRmZDk4NWE0LTg5NDUtNGRmZi1iYjZkLWFkODkwOTQ0NjJjOA.jpg");
		profile3.setPhotoUrls(urls3);
		
		
		XingProfile profile2 = new XingProfile("12345", "Dominique", "Phan", "http://google.com", "dominiquephan@gmail.com", "Dominique Phan");
		profile2.setBirthDate(new BirthDate(new Long(24),new Long(1996),new Long(25)));
		profile2.setGender("m");
		profile2.setHaves("Android,Swift,PHP");
		PhotoUrls urls2 = new PhotoUrls();
		urls2.setSize_128x128("https://assets.ubuntu.com/v1/590018e7-picto-ubuntukylin.svg");
		profile2.setPhotoUrls(urls2);
		profile2.setPrivateAddress(new PrivateAddress("fax","0618443522","0344248635","01000","France","Ain","Bourg-en-Bresse","rue des marchands","dominiquephan@losdiablos.evl"	));
		final Company firstCompany2 = new Company("Computing","30000",new XingDate("2001-01"),"businessTag","LosDiablosCoorp","http://losdiablos.evl",CareerLevel.PROFESSIONAL_EXPERIENCED,"Boss Manager",new XingDate("2010-01"),"Evil is our domain",	FormOfEmployment.FULL_TIME_EMPLOYEE,false);
		profile.setProfessionalExperience(new ProfessionalExperience(new ArrayList<Company>(Arrays.asList(firstCompany2)),firstCompany2, null));
		
		HashMap<Language,LanguageSkill> languages2 = new HashMap<>();
		languages2.put(Language.fr, LanguageSkill.NATIVE);
		//languages2.put(Language.de, LanguageSkill.GOOD);
		languages2.put(Language.en, LanguageSkill.GOOD);
		profile.setLanguages(languages2);
		profile.setEmploymentStatus(EmploymentStatus.UNEMPLOYED);
		
		School school2 = new School( "Computer Science", new XingDate("2000-01"), "third grade", "Evil school", new XingDate("2002-01"), "notes?");
		profile.setEducationalBackground(new EducationalBackground(school2,new ArrayList<>(Arrays.asList("diablos qualification")),new ArrayList<>(Arrays.asList(school2))));
		profile.setWants("Glory");
		
	
		
		
		xingRepo.save(profile);
		xingRepo.save(profile3);
		xingRepo.save(profile4);
		xingRepo.save(profile5);
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
		
		Contacts contact3 = new Contacts();
		List<XingProfile> contactProfiles3 = new ArrayList<>();
		contactProfiles3.add(profile);
		contact3.setUsers(contactProfiles3);
		profile3.setContacts(contact3);
		
		Contacts contact4 = new Contacts();
		List<XingProfile> contactProfiles4 = new ArrayList<>();
		contactProfiles4.add(profile);
		contactProfiles4.add(profile5);
		contactProfiles4.add(profile2);
		contact4.setUsers(contactProfiles4);
		profile4.setContacts(contact4);
		
		Contacts contact5 = new Contacts();
		List<XingProfile> contactProfiles5 = new ArrayList<>();
		contactProfiles5.add(profile4);
		contactProfiles5.add(profile3);
		contactProfiles5.add(profile2);
		contact5.setUsers(contactProfiles5);
		profile5.setContacts(contact5);
		
		

		contactsRepo.save(contacts);
		contactsRepo.save(contacts2);
		contactsRepo.save(contact3);
		contactsRepo.save(contact4);
		contactsRepo.save(contact5);
		xingRepo.save(profile);
		xingRepo.save(profile2);
		xingRepo.save(profile3);
		xingRepo.save(profile4);
		xingRepo.save(profile5);
		}
		
		return true;
	}
	

	public void updateProfile(XingProfile profile)
	{
		xingRepo.save(profile);
	}
	
	public Contacts contactsByProfileId(String profileId)
	{
		return xingRepo.findContactsByProfileId(profileId).getContacts();
	}
	
	public List<XingProfile> profilesByTag(String tag)
	{
		return (List<XingProfile>) xingRepo.findProfilesByTag("%"+tag+"%");
	}
	
	
	public XingProfile findByProfileId(String profileId)
	{
		return xingRepo.findProfileById(profileId);
	}
	
}
