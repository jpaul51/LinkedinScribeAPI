package dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import model.Favoris;

@Component
public interface FavorisRepository extends CrudRepository<Favoris, Long> {

	
	
	
	
}
