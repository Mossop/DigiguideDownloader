package com.blueprintit.digiguide;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.io.IOException;
import java.io.InputStream;

public class FileList extends FileDecoder
{
	List files;
	
	public FileList(InputStream in)
	{
		super(in);
		files = new LinkedList();
	}
	
	public Iterator iterator()
	{
		return files.iterator();
	}
	
	public void decode() throws IOException
	{
		readInt();
		String name = readSizedString();
		while (!isEOF())
		{
			RemoteFile file = new RemoteFile();
			file.setName(name);
			file.setDate(readDate());
			file.setLength(readInt());
			files.add(file);
			name=readSizedString();
		}
	}
	
	public static FileList getRemoteFileList() throws IOException
	{
		return new FileList(RemoteFile.getRemoteConnection("files.lst"));
	}
}
