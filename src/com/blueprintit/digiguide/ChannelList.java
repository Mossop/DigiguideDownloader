package com.blueprintit.digiguide;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class ChannelList extends FileDecoder
{
	List channels;
	
	public ChannelList(InputStream in) throws IOException
	{
		super(new GZIPInputStream(in));
		channels = new LinkedList();
	}
	
	public Iterator iterator()
	{
		return channels.iterator();
	}
	
	public void decode() throws IOException
	{
		readInt();
		readInt();
		int id=readInt();
		while (!isEOF())
		{
			Channel channel = new Channel();
			channel.setId(id);
			channel.setName(readSizedString());
			channel.setURL(readSizedString());
			channels.add(channel);
			id=readInt();
		}
	}
	
	public static ChannelList getRemoteChannelList() throws IOException
	{
		return new ChannelList(RemoteFile.getRemoteConnection("channels"));
	}
}
