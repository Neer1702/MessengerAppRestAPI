package org.neeraj.restwebservice.messenger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {
	
	private long id;
	private String profilename;
	private String firstName;
	private String lastname;
	
	public Profile()
	{}
	
	public Profile(long id, String profilename, String firstName, String lastname) {
		super();
		this.id = id;
		this.profilename = profilename;
		this.firstName = firstName;
		this.lastname = lastname;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProfilename() {
		return profilename;
	}
	public void setProfilename(String profilename) {
		this.profilename = profilename;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
