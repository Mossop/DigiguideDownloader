package com.blueprintit.digiguide;

import java.util.Date;
import java.util.Random;
import java.net.URLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class RemoteFile
{
	private String name;
	private Date date;
	private int length;
	
	public RemoteFile()
	{
	}
	
	public RemoteFile(String name, Date date, int length)
	{
		this();
		setLength(length);
		setDate(date);
		setName(name);
	}
	
	public InputStream getRemoteConnection() throws IOException
	{
		return getRemoteConnection(name);
	}
	
	public static String chooseHost() throws IOException
	{
		URLConnection conn = (new URL("http","www.DigiGuide.com","/download/hosts.txt")).openConnection();
		conn.setUseCaches(false);
		conn.setRequestProperty("User-Agent","Digiguide Fetch Agent 5.0 (Build 190)");
		conn.setRequestProperty("Cache-Control","no-cache");
		conn.connect();
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		List hosts = new ArrayList();
		String nexthost = in.readLine();
		while (nexthost!=null)
		{
			hosts.add(nexthost);
			nexthost=in.readLine();
		}
		String host=(String)hosts.get((new Random()).nextInt(hosts.size()));
		System.setProperty("digiguide.host",host);
		System.err.println("Using host "+host);
		in.close();
		return host;
	}
	
	public static InputStream getRemoteConnection(String filename) throws IOException
	{
		String host = System.getProperty("digiguide.host");
		if (host==null)
		{
			host=chooseHost();
		}
		URLConnection conn = (new URL("http",host,"/download/"+filename)).openConnection();
		conn.setUseCaches(false);
		conn.setRequestProperty("User-Agent","Digiguide Fetch Agent 5.0 (Build 190)");
		conn.setRequestProperty("Cache-Control","no-cache");
		conn.connect();
		return conn.getInputStream();
	}
	
	public void setName(String value)
	{
		name=value;
	}
	
	public String getName()
	{
		return name;
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
}
