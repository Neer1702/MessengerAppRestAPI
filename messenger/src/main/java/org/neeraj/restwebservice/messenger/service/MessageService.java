package org.neeraj.restwebservice.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.neeraj.restwebservice.messenger.database.DatabaseClass;
import org.neeraj.restwebservice.messenger.exception.DataNotFoundException;
import org.neeraj.restwebservice.messenger.model.Message;

public class MessageService {
	
	private Map<Long,Message> messages= DatabaseClass.getMessages();
	
	//to initialize value from mock db
	public MessageService ()  //the service constructor putting data in mock db(hashmap)
	{
		messages.put(1L, new Message(1L,"Hello World","Neeraj"));
		messages.put(2L,new Message(2L,"Hello jersey","Neeraj"));
	}
	
	//getting all messages
	public List<Message> getAllMessages()  //All messages from the hashmap of data /model made
	{
		return new ArrayList<Message>(messages.values());
	}
	
	//getting one message
	public Message getMessage(long id)  //from data/model hashmap getting the desired message
	{
		Message message= messages.get(id);
		if(message==null)
		{
			throw new DataNotFoundException("Message with id "+id+" not found ");
		}
		return message;
	}
	
	//get all messages for year
	public List<Message> getAllMessageForYear(int year)  //from data/model hashmap getting the desired message
	{
		List<Message> messagesForYear= new ArrayList<>();
		Calendar cal=Calendar.getInstance();
		for(Message message:messages.values())
		{
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR)==year)
			{
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	//get all messages betweeen dates
	public List<Message> getAllMessagePaginated(int start,int size)  //from data/model hashmap getting the desired message
	{
		ArrayList<Message> list= new ArrayList<>(messages.values());
		if(start+size>list.size()) return new ArrayList<Message>();
		return list.subList(start,start+size);
	}
	
	//adding new messages
	public Message addMessage(Message message)  
	{
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	//update existing message
	public Message updateMessage(Message message) 
	{
		if(message.getId()<=0) return null;
		
		messages.put(message.getId(), message);
		return message;
	}
	
	//removal of messages
	public Message removeMessage(long id)  
	{
		return messages.remove(id);
	}

	 
	
	

}
