package com.blueprintit.digiguide;

public class Category
{
	private String name;
	private int id;
	
	public Category()
	{
	}
	
	public Category(String name, int id)
	{
		this();
		setId(id);
		setName(name);
	}
	
	public String getSQL()
	{
		return "INSERT IGNORE INTO Category (id,name) VALUES ("+id+","+DBHelper.formatString(name)+");";
	}
	
	public void setName(String value)
	{
		name=value;
	}
	
	public String getName()
	{
		return name;
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
