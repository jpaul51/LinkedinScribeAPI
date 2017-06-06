package services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AdminRepository;
import dao.FavorisRepository;
import model.Admin;
import model.Favoris;
import model.XingProfile;

@Service
public class AdminService {


	@Autowired AdminRepository adminRepo;
	@Autowired FavorisRepository favRepo;


	public Admin login(String name,String password)
	{
		return adminRepo.login(name, password);
	}

	public boolean adminExists(String name)
	{
		return adminRepo.adminExists(name)!=null;
	}

	public void addAdmin(Favoris fav,Admin a)
	{
		//		System.out.println("trying to save "+fav.toString()+", SIZE: "+fav.getFavList().size());
		//		System.out.println("FIRST PROFILE: "+fav.getFavList().get(0).getId()+", "+fav.getFavList().get(0).getIdLong());
		favRepo.save(fav);
		System.out.println("save fav");
		adminRepo.save(a);
		System.out.println("save admin");
	}

	public void addAdmin(Admin a)
	{

		adminRepo.save(a);

	}

	public HashMap<String,String> removeFavoris(XingProfile profileToEdit, Admin admin, String favTitle)
	{
		HashMap<String,String> ret = new HashMap<>();
		
		if(admin.getFav().isEmpty())
		{
			ret.put("code", "KO");
			ret.put("message", "Empty list");
		}
		
		boolean titleFound=false;
		String removedId="";
		for(Favoris f : admin.getFav())
		{
			if(f.getTitle().equals(favTitle))
			{
				titleFound=true;
				if(f.getFavList().contains(profileToEdit)){
					f.getFavList().remove(profileToEdit);
					removedId = profileToEdit.getId();
				}
			}
		}
		
		if(!titleFound)
		{
			ret.put("code", "KO");
			ret.put("message", "List not found");
		}
		else if(!removedId.equals(""))
		{
			ret.put("code", "OK");
			ret.put("message", "Removed id: "+removedId);
		}
		else
		{
			ret.put("code", "KO");
			ret.put("message", "Given profile id is not in list");
		}
		adminRepo.save(admin);
		return ret;
		
	}


}
