package com.blueprintit.digiguide;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DBHelper
{
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String formatString(String value)
	{
		return "'"+value.replaceAll("\\\\","\\\\\\\\").replaceAll("'","\\\\'")+"'";
	}
	
	public static String formatDate(Date value)
	{
		return "'"+df.format(value)+"'";
	}
}
