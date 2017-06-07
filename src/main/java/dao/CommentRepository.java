package dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import model.Comment;

@Component
public interface CommentRepository extends CrudRepository<Comment, Long> {

	
	
	
	
}
