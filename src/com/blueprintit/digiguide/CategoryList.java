package com.blueprintit.digiguide;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class CategoryList extends FileDecoder
{
	List categorys;
	
	public CategoryList(InputStream in) throws IOException
	{
		super(new GZIPInputStream(in));
		categorys = new LinkedList();
	}
	
	public Iterator iterator()
	{
		return categorys.iterator();
	}
	
	public void decode() throws IOException
	{
		readInt();
		readInt();
		int id=readInt();
		while (!isEOF())
		{
			Category category = new Category();
			category.setId(id);
			category.setName(readSizedString());
			categorys.add(category);
			readInt();
			readInt();
			id=readInt();
		}
	}
	
	public static CategoryList getRemoteCategoryList() throws IOException
	{
		return new CategoryList(RemoteFile.getRemoteConnection("category"));
	}
}
