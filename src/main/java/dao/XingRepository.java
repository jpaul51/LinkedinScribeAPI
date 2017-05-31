package dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import model.XingProfile;

import org.springframework.data.jpa.repository.Query;

@Component
public interface XingRepository extends CrudRepository<XingProfile, Long>{

	@Query(value="Select l from XingProfile l where l.if = :profile_id ")
	public XingProfile findProfileById(@Param("profile_id") String profileId);
	
	
	
}
