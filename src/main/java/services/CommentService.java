package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CommentRepository;
import dao.CommentsRepository;
import model.Comment;
import model.Comments;

@Service
public class CommentService {

	
	@Autowired CommentsRepository commentsRepo;
	@Autowired CommentRepository commentRepo;
	
	public void deleteComments(Comments comment)
	{
		commentsRepo.delete(comment);
	}
	
	public void updateComments(Comments comment)
	{
		commentsRepo.save(comment);
	}
	public void deleteComment(Comment comment)
	{
		commentRepo.delete(comment);
	}
	
	public void updateComment(Comment comment)
	{
		commentRepo.save(comment);
	}
	
}
