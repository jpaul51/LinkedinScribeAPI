package dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import model.Comments;
@Component
public interface CommentsRepository extends CrudRepository<Comments, Long> {

	
	
	
}
