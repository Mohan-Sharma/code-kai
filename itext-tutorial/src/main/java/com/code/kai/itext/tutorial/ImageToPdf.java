package com.code.kai.itext.tutorial;

import com.google.common.collect.Lists;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * @author Mohan Sharma Created on 25/11/17.
 * This example demonstrates how to convert image file to PDF file.
 */
public class ImageToPdf
{
    static final List<String> ALLOWED_IMAGES = Lists.newArrayList("jpg", "jpeg", "png", "gif");
    public static final String GENERATED_PDF_FILE_NAME = "/generatedPDF.pdf";

    public static void main(String arr[]) throws IOException, DocumentException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide the absolute path of the source folder containing all the images");
        String sourceFile = scanner.nextLine();
        System.out.println("Please provide the absolute path of the target folder");
        String targetFile = scanner.nextLine();
        ImageToPdf imageToPdf = new ImageToPdf();
        imageToPdf.convertImagesToPDFFile(sourceFile, targetFile);
    }

    public void convertImagesToPDFFile(String srcFile, String descFile) throws IOException, DocumentException
    {
        try(FileOutputStream writer = new FileOutputStream(descFile + GENERATED_PDF_FILE_NAME))
        {
            Document document = new Document(PageSize.A4, 10f, 10f, 10f, 10f);
            PdfWriter.getInstance(document, writer);
            document.open();
            float width = document.getPageSize().getWidth() - (10f + 10f);
            float height = document.getPageSize().getHeight() - (10f + 10f);
            Path sourcePath = Paths.get(srcFile);
            Files
                    .list(sourcePath)
                    .filter(Files::exists)
                    .filter(Files::isRegularFile)
                    .filter(imagePath -> imagePath.toString().lastIndexOf(".") != -1 && imagePath.toString().lastIndexOf(".") != 0)
                    .filter(imagePath ->
                    {
                        String imageExtension = imagePath.toString().substring(imagePath.toString().lastIndexOf(".") + 1).toLowerCase();
                        return ALLOWED_IMAGES.contains(imageExtension);
                    })
                    .forEach(imagePath -> {
                        try
                        {
                            Image image = Image.getInstance(imagePath.toString());
                            image.setAbsolutePosition(10, 10);
                            image.scaleToFit(width, height);
                            document.add(image);
                            document.newPage();
                        }
                        catch (DocumentException e)
                        {
                            e.printStackTrace();
                        }
                        catch (MalformedURLException e)
                        {
                            e.printStackTrace();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    });
            document.close();
        }
    }
}


