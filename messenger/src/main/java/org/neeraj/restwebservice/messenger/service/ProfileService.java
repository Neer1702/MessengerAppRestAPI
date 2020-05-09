package org.neeraj.restwebservice.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.neeraj.restwebservice.messenger.database.DatabaseClass;
import org.neeraj.restwebservice.messenger.model.Message;
import org.neeraj.restwebservice.messenger.model.Profile;

public class ProfileService {

	private Map<String,Profile> profiles= DatabaseClass.getProfiles();
	
	public ProfileService()
	{
		profiles.put("neeraj",new Profile(1L,"neeraj","Neeraj","Bamnotey"));
		profiles.put("urja",new Profile(2L,"urja","Urja","Bamnotey"));
	}
	
	public List<Profile> getAllProfiles()  //All messages from the hashmap of data /model made
	{

		return new ArrayList<Profile>(profiles.values());
//		instantiating the model here in sevice only		
//		Message m1= new Message(1L,"Hello World","Neeraj");
//		Message m2= new Message(2L,"Hello Jersey","Neeraj");
//		
//		List<Message> listM= new ArrayList<>();
//		listM.add(m1);
//		listM.add(m2);
		
//		return listM;
		
	}
	
	
	public Profile getProfile(String profileName)  //from data/model hashmap getting the desired message
	{
		return profiles.get(profileName);
	}
	
	public Profile addProfiles(Profile profile)  //adding new mssage to the hasmap taking as input
	{
		profile.setId(profiles.size()+1);
		profiles.put(profile.getProfilename(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile)  //updating the existing message
	{
		if(profile.getProfilename().isEmpty()) return null;
		
		profiles.put(profile.getProfilename(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profilename)  //updating the existing message
	{
		return profiles.remove(profilename);
	}
}
