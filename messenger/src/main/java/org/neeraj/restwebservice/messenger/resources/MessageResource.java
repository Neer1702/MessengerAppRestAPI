 package org.neeraj.restwebservice.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.neeraj.restwebservice.messenger.model.Message;
import org.neeraj.restwebservice.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value ={MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class MessageResource {
    //configure the resource
	MessageService serv= new MessageService();
	//for all messages
	@GET
	@Produces(MediaType.APPLICATION_JSON) //@Produces(MediaType.APPLICATION_XML)  
	public List<Message> getMessages(@QueryParam("year") int year,
			@QueryParam("start") int start,@QueryParam("size") int size)  //for get we canhave only one method so here taking arguements as quer param
	{
		if(year>0)
		{
			return serv.getAllMessageForYear(year);
		}
		if(start >0 && size>0)
		{
			return serv.getAllMessagePaginated(start,size);
		}
		return serv.getAllMessages();
	} 
	
	
	
	//adding messages
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message , @Context UriInfo uriInfo) throws URISyntaxException   //instead of returning just the added message , establishing more control over the response by returning the response object
	{											//getting  uriInfo instance
		Message newMessage = serv.addMessage(message);
		String newId=String.valueOf(newMessage.getId());
		URI uri=uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
		//return Response.created(new URI("/messenger/webapi/messages/" + newMessage.getId())) //can have the added message uri in the respose header
		//return Response.status(Status.CREATED)  //success and CREATED message with code: cookie and encoding can also be set
		.entity(newMessage)
		.build();
		//return serv.addMessage(message);
	}
	
	//getting for one message messages
	@GET
	@Path("/{messageId}")// so for seperate methods also we have 
					 //@path .. so outside class its /messages.. 
		            //for this method we need /messages/test.. so just "/test"
					//inside{} there is variable any variable passed and again the 
					//configuration is done in such a way that the varaible will be used to 
					//access data
	@Produces(MediaType.APPLICATION_JSON) //@Produces(MediaType.APPLICATION_XML)  
	public Message getMessage(@PathParam("messageId")long messageId , @Context UriInfo uriInfo)
	{
		Message message = serv.getMessage(messageId);
		String  uri =uriInfo.getBaseUriBuilder()					//http://localhost:8080/messenger/webapi
		.path(MessageResource.class)				//									/messages
		.path(Long.toString(message.getId()))		//										/{messageId}
		.build()
		.toString();
		message.addLink(uri, "self");
		message.addLink(getUriForProfile(uriInfo,message),"profile");
		message.addLink(getUriForComment(uriInfo,message),"comments");
		return message;
	} 	
	
	private String getUriForComment (UriInfo uriInfo , Message message)
	{
		URI uri =uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class,"getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
				.path(message.getAuthor())
				.build();
		return uri.toString();
	}
	
	
	private String getUriForProfile(UriInfo uriInfo , Message message)
	{
		URI uri =uriInfo.getBaseUriBuilder()
				.path(Profileresource.class)
				.path(message.getAuthor())
				.build();
		return uri.toString();
	}
	
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id ,Message message)
	{
		message.setId(id);	
		return serv.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long id)
	{
		serv.removeMessage(id);
	}
	
	@Path("/{messageId}/comments")     //for a sub resource
	public CommentResource getCommentResource()
	{
		return new CommentResource();
	}
	 
	
}
