package com.blueprintit.digiguide;

import java.io.InputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class FileDecoder
{
	private DataInputStream input;
	private boolean eof;
	
	protected FileDecoder(InputStream in)
	{
		input = new DataInputStream(in);
		eof=false;
	}
	
	public abstract void decode() throws IOException;
	
	protected String readSizedString() throws IOException
	{
		int size = readInt();
		if (!isEOF())
		{
			return readString(size);
		}
		else
		{
			return null;
		}
	}
	
	protected String readString(int length) throws IOException
	{
		byte[] buffer = new byte[length];
		try
		{
			input.readFully(buffer);
			return new String(buffer);
		}
		catch (EOFException e)
		{
			eof=true;
			return null;
		}
	}
	
	protected int readInt() throws IOException
	{
		try
		{
			int result = input.readUnsignedByte();
			result=result+(input.readUnsignedByte()<<8);
			result=result+(input.readUnsignedByte()<<16);
			result=result+(input.readUnsignedByte()<<24);
			return result;
		}
		catch (EOFException e)
		{
			eof=true;
			return -1;
		}
	}
	
	protected int readShort() throws IOException
	{
		try
		{
			int result = input.readUnsignedByte();
			result=result+(input.readUnsignedByte()<<8);
			return result;
		}
		catch (EOFException e)
		{
			eof=true;
			return -1;
		}
	}
	
	protected int readByte() throws IOException
	{
		try
		{
			return input.readUnsignedByte();
		}
		catch (Exception e)
		{
			eof=true;
			return -1;
		}
	}
	
	protected Date readDate() throws IOException
	{
		try
		{
			int day = input.readUnsignedByte();
			int month = input.readUnsignedByte();
			int year = readShort();
			int hour = input.readUnsignedByte();
			int minute = input.readUnsignedByte();
			int second = input.readUnsignedByte();
			return (new GregorianCalendar(year,month,day,hour,minute,second)).getTime();
		}
		catch (EOFException e)
		{
			eof=true;
			return null;
		}
	}
	
	protected boolean isEOF()
	{
		return eof;
	}
}
