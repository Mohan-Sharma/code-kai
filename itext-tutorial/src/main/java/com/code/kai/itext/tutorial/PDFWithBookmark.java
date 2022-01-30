package com.code.kai.itext.tutorial;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Mohan Sharma Created on 25/11/17.
 * This example demonstrates how to convert text file to PDF file.
 */
public class PDFWithBookmark
{
    public static void main(String[] args) throws IOException, DocumentException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the absolute path of the source file");
        String sourceFile = scanner.nextLine();
        PDFWithBookmark pdfWithBookmark = new PDFWithBookmark();
        List<Bookmark> bookmarks = getBookmarkList(pdfWithBookmark);
        pdfWithBookmark.createPDFWithBookmark(bookmarks, sourceFile);
        System.out.println("***************** sucessfully done *****************");
    }

    /**
     * This function demonstrates how to read a property file which contains bookmark definitions.
     * @param pdfWithBookmark
     * @return list of bookmark to be linked to the pdf
     * @throws IOException
     */
    private static List<Bookmark> getBookmarkList(PDFWithBookmark pdfWithBookmark) throws IOException
    {
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("bookmark.properties"));
        return properties
                .entrySet()
                .stream()
                .map(property -> pdfWithBookmark.new Bookmark(property.getKey().toString(),  Integer.parseInt(property.getValue().toString())))
                .collect(Collectors.toList());
    }

    /**
     * This function demonstrates how to read a existing pdf and add bookmark to it.
     * @param bookmarkList
     * @param sourceFile
     * @throws IOException
     * @throws DocumentException
     */
    private void createPDFWithBookmark(List<Bookmark> bookmarkList, String sourceFile)
            throws IOException, DocumentException
    {
        try (OutputStream outputStream = new FileOutputStream(sourceFile.replace(".pdf", "-bookmarked.pdf")))
        {
            ArrayList<HashMap<String, Object>> outlines = new ArrayList<>();
            bookmarkList.stream().forEach(bookmark -> {
                HashMap<String, Object> map = new HashMap<>();
                map.put("Title", bookmark.getBookmarkTitle());
                map.put("Action", "GoTo");
                map.put("Page", String.format("%d", bookmark.getPageNumber()));
                outlines.add(map);
            });

            PdfReader reader = new PdfReader(sourceFile);
            PdfStamper stamper = new PdfStamper(reader, outputStream);
            stamper.setOutlines(outlines);
            stamper.close();
            reader.close();
        }
    }

    /**
     * This class is used to create bookmark definitions to be used.
     */
    class Bookmark
    {
        private String bookmarkTitle;
        private int pageNumber;

        public Bookmark(String bookmarkTitle, int pageNumber)
        {
            this.bookmarkTitle = bookmarkTitle;
            this.pageNumber = pageNumber;
        }

        public String getBookmarkTitle()
        {
            return bookmarkTitle;
        }

        public int getPageNumber()
        {
            return pageNumber;
        }
    }
}