package com.code.kai.itext.tutorial;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Mohan Sharma Created on 25/11/17.
 * This example demonstrates how to convert text file to PDF file.
 */
public class TextToPdf
{

    public static final String GENERATED_PDF_FILE_NAME = "/output.pdf";

    public static void main(String arr[])
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the absolute path of the source file");
        String sourceFile = scanner.nextLine();
        System.out.println("Please provide the absolute path of the target folder");
        String targetFile = scanner.nextLine();
        TextToPdf textToPdf = new TextToPdf();
        textToPdf.createPDFFromTextFile(sourceFile, targetFile);
    }

    /**
     * This function reads the text file and creates a PDF file at the specified path.
     * @param srcFile
     * @param descFile
     */
    public void createPDFFromTextFile(String srcFile, String descFile)
    {
        try(
                FileReader reader = new FileReader(srcFile);
                FileOutputStream writer = new FileOutputStream(descFile + GENERATED_PDF_FILE_NAME)
        )
        {
            Document document = new Document();
            PdfWriter.getInstance(document, writer);
            document.open();
            BufferedReader br = new BufferedReader(reader);
            String line;
            Paragraph p;
            Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 12);
            while ((line = br.readLine()) != null)
            {
                p = new Paragraph(line, normal);
                p.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(p);
            }
            document.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }
    }
}
