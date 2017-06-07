package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CommentsRepository;
import model.Comments;

@Service
public class CommentService {

	
	@Autowired CommentsRepository commentRepo;
	
	public void deleteComment(Comments comment)
	{
		commentRepo.delete(comment);
	}
	
	public void updateComment(Comments comment)
	{
		commentRepo.save(comment);
	}
	
}
