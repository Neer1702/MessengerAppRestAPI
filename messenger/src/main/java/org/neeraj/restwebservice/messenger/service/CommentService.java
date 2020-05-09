package org.neeraj.restwebservice.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.neeraj.restwebservice.messenger.database.DatabaseClass;
import org.neeraj.restwebservice.messenger.model.Comment;
import org.neeraj.restwebservice.messenger.model.ErrorMessage;
import org.neeraj.restwebservice.messenger.model.Message;

public class CommentService {
	
	private Map<Long,Message> messages = DatabaseClass.getMessages();
	
	public List<Comment> getAllComments(long messageId)
	{
		Map<Long,Comment> comments= messages.get(messageId).getComments();
		return new ArrayList<Comment> (comments.values());
	}
	
	public Comment getComment(long messageId,long commentId)
	{
		ErrorMessage errorMessage = new ErrorMessage("Not Found",404," ");
		 Response response =Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build(); 
		Message message = messages.get(messageId);
		if(message == null)
		{
			throw new WebApplicationException(response); //no need to created mappers as WebApplicationException registers them in JAX RS so Jersey knows 
		}
		Map<Long,Comment> comments= messages.get(messageId).getComments();
		Comment comment = comments.get(commentId);
		if(comment==null)
		{
			throw new WebApplicationException(response);
		}
		return comments.get(commentId);
	}
	
	public Comment addComment(long messageId,Comment comment)
	{
		Map<Long,Comment> comments= messages.get(messageId).getComments();
		comment.setid(comments.size()+1);
		comments.put(comment.getid(),comment);
		return comment;
	}
	
	public Comment updateComment(long messageId,Comment comment)
	{
		Map<Long,Comment> comments= messages.get(messageId).getComments();
		if(comment.getid()<=0)
		{
			return null;
		}
		comments.put(comment.getid(),comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId)
	{
		Map<Long,Comment> comments= messages.get(messageId).getComments();
		return comments.remove(commentId);
		
	}

}
