package org.neeraj.restwebservice.messenger.resources;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.neeraj.restwebservice.messenger.model.Message;
import org.neeraj.restwebservice.messenger.model.Profile;
import org.neeraj.restwebservice.messenger.service.ProfileService;

@Path("/profiles")
public class Profileresource {

//creating the object to access service methods
	ProfileService profileservice= new ProfileService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Profile> getAllProfiles()
	{
		return profileservice.getAllProfiles();
	}
	
	@GET
	@Path("/{profilename}")
	@Produces(MediaType.APPLICATION_JSON)
	public Profile getProfile(@PathParam("profilename") String name)
	{
		return profileservice.getProfile(name);
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Profile addProfile(Profile profile)
	{
		return profileservice.addProfiles(profile);
	}
	
	@PUT
	@Path("/{profilename}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Profile updateMessage(@PathParam("profilename") String name,Profile profile)
	{
		profile.setProfilename(name);	
		return profileservice.updateProfile(profile);
	}
	@DELETE
	@Path("/{profilename}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("profilename") String name)
	{
		profileservice.removeProfile(name);
	}
	
	
	
	
}
