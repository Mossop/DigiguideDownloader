package com.blueprintit.digiguide;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class ScheduleDecoder extends FileDecoder
{
	List programmes;
	
	public ScheduleDecoder(InputStream in) throws IOException
	{
		super(new GZIPInputStream(in));
		programmes = new LinkedList();
	}
	
	public Iterator iterator()
	{
		return programmes.iterator();
	}
	
	public void decode() throws IOException
	{
		readInt();
		readInt();
		readInt();
		readInt();
		int channel = readInt();
		while (!isEOF())
		{
			int count = readInt();
			for (int loop=0; loop<count; loop++)
			{
				Programme prog = new Programme();
				prog.setChannel(channel);
				prog.setDate(readDate());
				prog.setLength(readShort());
				readInt();
				prog.setTitle(readSizedString());
				prog.setCategory(readShort());
				prog.setDescription(readSizedString());
				readInt();
				int skip = readInt()*2;
				for (int sloop=0; sloop<skip; sloop++)
				{
					readByte();
				}
				prog.setId(readInt());
				programmes.add(prog);
			}
			channel=readInt();
		}
	}
}
