package com.code.kai.itext.tutorial;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSmartCopy;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Mohan Sharma Created on 22/12/17.
 * This example demonstrates how to merge multiple PDF file to a single PDF file.
 */
public class MergeMultiplePDF
{

	public static final String MERGED_PDF_FILE_NAME = "/MERGED.pdf";

	public void mergeFiles(String srcFolder, String descFile) throws IOException, DocumentException
	{
		Document document = new Document();
		PdfSmartCopy copy = new PdfSmartCopy(document, new FileOutputStream(descFile));
		document.open();
		Path sourcePath = Paths.get(srcFolder);
		Files
				.list(sourcePath)
				.filter(Files::exists)
				.filter(Files::isRegularFile)
				.filter(pdf -> pdf.toString().toLowerCase().endsWith(".pdf"))
				.collect(Collectors.toList())
				.forEach(pdfFilePath -> {
					try(FileInputStream fileInputStream = new FileInputStream(pdfFilePath.toFile()))
					{
						PdfReader reader = new PdfReader(fileInputStream);
						for(int j = 1; j <= reader.getNumberOfPages(); j++)
						{
							copy.addPage(copy.getImportedPage(reader, j));
						}
						copy.freeReader(reader);
						reader.close();
					}
					catch (FileNotFoundException e)
					{
						e.printStackTrace();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					catch (BadPdfFormatException e)
					{
						e.printStackTrace();
					}
				});
		document.close();
	}

	public static void main(String[] args) throws IOException, DocumentException
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please provide the absolute path of the source folder");
		String sourceFolder = scanner.nextLine();
		System.out.println("Please provide the absolute path of the target folder");
		String targetFolder = scanner.nextLine();
		MergeMultiplePDF mergeMultiplePDF = new MergeMultiplePDF();
		mergeMultiplePDF.mergeFiles(sourceFolder, targetFolder + MERGED_PDF_FILE_NAME);
	}
}
