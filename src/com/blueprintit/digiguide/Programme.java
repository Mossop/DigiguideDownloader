package com.blueprintit.digiguide;

import java.util.Date;

public class Programme
{
	private String title;
	private Date date;
	private int channel;
	private int id;
	private int length;
	private int category;
	private String description;
	
	public Programme()
	{
	}
	
	public void setTitle(String value)
	{
		title=value;
	}
	
	public String getSQL()
	{
		return "INSERT IGNORE INTO Programme (id,category,channel,date,length,title,description) "+
						"VALUES ("+id+","+category+","+channel+","+DBHelper.formatDate(date)+
						","+length+","+DBHelper.formatString(title)+","+
						DBHelper.formatString(description)+");";
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setDescription(String value)
	{
		description=value;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDate(Date value)
	{
		date=value;
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public void setLength(int value)
	{
		length=value;
	}
	
	public int getLength()
	{
		return length;
	}
	
	public void setId(int value)
	{
		id=value;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setChannel(int value)
	{
		channel=value;
	}
	
	public int getChannel()
	{
		return channel;
	}
	
	public void setCategory(int value)
	{
		category=value;
	}
	
	public int getCategory()
	{
		return category;
	}
}
