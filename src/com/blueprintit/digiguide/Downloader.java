
package com.blueprintit.digiguide;

import java.util.Iterator;
import java.io.IOException;

public class Downloader
{
	public Downloader()
	{
	}
	
	public void download() throws IOException
	{
		System.err.println("Downloading channels");
		ChannelList channels = ChannelList.getRemoteChannelList();
		channels.decode();
		Iterator loop = channels.iterator();
		while (loop.hasNext())
		{
			System.out.println(((Channel)loop.next()).getSQL());
		}
		System.err.println("Downloading categories");
		CategoryList cats = CategoryList.getRemoteCategoryList();
		cats.decode();
		loop = cats.iterator();
		while (loop.hasNext())
		{
			System.out.println(((Category)loop.next()).getSQL());
		}
		FileList list = FileList.getRemoteFileList();
		list.decode();
		loop = list.iterator();
		while (loop.hasNext())
		{
			RemoteFile file = (RemoteFile)loop.next();
			if (file.getName().endsWith(".pat"))
			{
				System.err.println("Downloading "+file.getName());
				ScheduleDecoder decoder = new ScheduleDecoder(file.getRemoteConnection());
				decoder.decode();
				Iterator ploop = decoder.iterator();
				while (ploop.hasNext())
				{
					System.out.println(((Programme)ploop.next()).getSQL());
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		(new Downloader()).download();
	}
}
