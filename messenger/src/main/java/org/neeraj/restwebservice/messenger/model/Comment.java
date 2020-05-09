package org.neeraj.restwebservice.messenger.model;

import java.util.Date;

public class Comment {

	private long id;
	private String message;
	private Date created;
	
	
	public Comment(long id, String message, Date created, String author) {
		super();
		this.id = id;
		this.message = message;
		this.created = new Date();
		this.author = author;
	}
	public long getid() {
		return id;
	}
	public void setid(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	private String author;
	
}
