package dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import model.Admin;

@Component
public interface AdminRepository extends CrudRepository<Admin, Long> {

	
	@Query("Select a from Admin a where a.name=:name AND a.password=:password")
	public Admin login(@Param("name")String name,@Param("password")String password);
	

	@Query("Select a from Admin a where a.name=:name")
	public Admin adminExists(@Param("name")String name);
	
	
	
}
