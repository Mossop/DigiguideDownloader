package com.blueprintit.digiguide;

public class Channel
{
	private String name;
	private String url;
	private int id;
	
	public Channel()
	{
	}
	
	public Channel(String name, String url, int id)
	{
		this();
		setId(id);
		setURL(url);
		setName(name);
	}
	
	public String getSQL()
	{
		return "INSERT IGNORE INTO Channel (id,name,url) VALUES ("+id+","+DBHelper.formatString(name)+","+DBHelper.formatString(url)+");";
	}
	
	public void setName(String value)
	{
		name=value;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setURL(String value)
	{
		url=value;
	}
	
	public String getURL()
	{
		return url;
	}
	
	public void setId(int value)
	{
		id=value;
	}
	
	public int getId()
	{
		return id;
	}
}
