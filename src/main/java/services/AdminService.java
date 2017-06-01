package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AdminRepository;
import dao.FavorisRepository;
import model.Admin;
import model.Favoris;

@Service
public class AdminService {

	
	@Autowired AdminRepository adminRepo;
	@Autowired FavorisRepository favRepo;
	
	
	public Admin login(String name,String password)
	{
		return adminRepo.login(name, password);
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
	
	
	
	
}
