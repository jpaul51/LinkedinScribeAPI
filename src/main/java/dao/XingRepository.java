package dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import model.XingProfile;

	@Component
	public interface XingRepository extends CrudRepository<XingProfile, Long>{
		
		@Query(value="Select l from XingProfile l where l.id = :profile_id ")
		public XingProfile findProfileById(@Param("profile_id") String profileId);
		
		@Query(value="Select l from XingProfile l where l.id = :profile_id ")
		public XingProfile findContactsByProfileId(@Param("profile_id") String profileId);
		
		@Query(value="Select l from XingProfile l where id like :tag OR interests like :tag OR wants like :tag OR firstName like :tag OR lastName like :tag OR displayName like :tag OR haves like :tag ")
		public ArrayList<XingProfile> findProfilesByTag(@Param("tag")String tag);	
	}
 




