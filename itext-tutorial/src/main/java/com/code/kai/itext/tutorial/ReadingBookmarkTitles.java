package com.code.kai.itext.tutorial;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.SimpleBookmark;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import org.apache.commons.collections.CollectionUtils;

/**
 * This class demonstrates how to read bookmark titles from existing PDF file.
 * @author Mohan Sharma Created on 23/12/17.
 */
public class ReadingBookmarkTitles
{
	/**
	 * This function demonstrates how to read a PDF file and get the bookmark titles of that file.
	 * @param sourceFile
	 * @throws IOException
	 */
	public void printBookmarksFromPDF(final String sourceFile) throws IOException
	{
		PdfReader reader = new PdfReader(sourceFile);
		List<HashMap<String,Object>> bookmarks = SimpleBookmark.getBookmark(reader);
		if(CollectionUtils.isNotEmpty(bookmarks))
		{
			bookmarks
					.stream()
					.forEach(titleMap -> displayTitles(titleMap));
		}
		reader.close();
	}

	/**
	 * This function demonstrates printing the bookmark titles provided as a map.
	 * @param titleMap
	 */
	private void displayTitles(Map<String, Object> titleMap)
	{
		if(Objects.isNull(titleMap))
			return;
		System.out.println("Bookmark Title is  : " + titleMap.get("Title") + " and linked with page number : " + titleMap.get("Page"));
		Collection<Object> values = titleMap.values();
		if(CollectionUtils.isNotEmpty(values))
		{
			values
					.stream()
					.filter(mapOfTitle -> mapOfTitle instanceof Map)
					.forEach(mapOfTitle -> displayTitles((Map<String, Object>) mapOfTitle));
		}
	}

	public static void main(String[] args) throws IOException
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please provide the absolute path of the source file");
		String sourceFile = scanner.nextLine();
		ReadingBookmarkTitles bookmarkTitles = new ReadingBookmarkTitles();
		bookmarkTitles.printBookmarksFromPDF(sourceFile);
	}
}
