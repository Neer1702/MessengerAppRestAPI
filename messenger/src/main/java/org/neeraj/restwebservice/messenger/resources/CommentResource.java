package org.neeraj.restwebservice.messenger.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.neeraj.restwebservice.messenger.model.Comment;
import org.neeraj.restwebservice.messenger.service.CommentService;

@Path("/")				//optional
public class CommentResource {

	
	private CommentService commServ= new CommentService();
	
	@GET 
	public List<Comment> getAllComments(@PathParam("messageId") long messageId)
	{
		return commServ.getAllComments(messageId);
	}
	
	@POST
	public Comment addComment(@PathParam("messageId") long messageId,Comment comment)
	{
		return commServ.addComment(messageId, comment);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateComments(@PathParam("messageId") long messageId , @PathParam("commentId") long id,Comment comment )
	{
		comment.setid(id);
		return commServ.updateComment(messageId, comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public void getAllComments(@PathParam("messageId") long messageId , @PathParam("commentId") long commentId )
	{
		commServ.removeComment(messageId, commentId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") long messageId,@PathParam("commentId") long commentId)
	{
		return commServ.getComment(messageId, commentId);
	}
	
	
}
