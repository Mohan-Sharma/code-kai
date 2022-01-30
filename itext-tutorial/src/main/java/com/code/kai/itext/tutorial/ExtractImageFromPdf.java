package com.code.kai.itext.tutorial;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfContentStreamProcessor;
import com.itextpdf.text.pdf.parser.PdfImageObject;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class demonstrates how to extract images from PDF file.
 * @author Mohan Sharma Created on 26/12/17.
 */
public class ExtractImageFromPdf
{
	/**
	 * This function is used to read the PDF file from source and extract image and store
	 * it in the output folder.
	 * @param sourceFile
	 * @param targetFolder
	 * @throws IOException
	 */
	public void extractImage(String sourceFile, String targetFolder) throws IOException
	{
		File outputFolder = new File(targetFolder);
		if(!outputFolder.exists())
			outputFolder.mkdir();
		System.out.println("=============================================================");
		System.out.println("*************Extracting Images From PDF**********************");
		System.out.println("=============================================================");
		PdfReader reader = new PdfReader(sourceFile);
		PdfReaderContentParser parser = new PdfReaderContentParser(reader);
		MyImageRenderListener listener = this.new MyImageRenderListener(outputFolder.getAbsolutePath() + File.separator + "%s.%s");
		for (int i = 1; i <= reader.getNumberOfPages(); i++)
		{
			parser.processContent(i, listener);
		}
		System.out.println("=============================================================");
		System.out.println("*************Done Extracting Images From PDF*****************");
		System.out.println("=============================================================\n");
	}

	/**
	 * An implementation of callback interface that receives notifications from the {@link PdfContentStreamProcessor}
	 * as various render operations are required.
	 */
	class MyImageRenderListener implements RenderListener
	{
		private String path;

		public MyImageRenderListener(String path)
		{
			this.path = path;
		}

		@Override
		public void beginTextBlock()
		{}

		@Override
		public void endTextBlock()
		{}

		/**
		 * this function gets a callback when images are to be rendered.
		 * @param renderInfo
		 */
		@Override
		public void renderImage(ImageRenderInfo renderInfo)
		{
			FileOutputStream os = null;
			try
			{
				PdfImageObject image = renderInfo.getImage();
				if (image == null)
				{
					return;
				}
				String filename = String.format(this.path,
						new Object[] { Integer.valueOf(renderInfo.getRef().getNumber()), image.getFileType() });
				os = new FileOutputStream(filename);
				os.write(image.getImageAsBytes());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					os.flush();
					os.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		@Override
		public void renderText(TextRenderInfo renderInfo)
		{}
	}

	public static void main(String[] args) throws IOException
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please provide the absolute path of the source file");
		String sourceFile = scanner.nextLine();
		System.out.println("Please provide the absolute path of the target folder");
		String targetFolder = scanner.nextLine();
		ExtractImageFromPdf extractImageFromPdf = new ExtractImageFromPdf();
		extractImageFromPdf.extractImage(sourceFile, targetFolder + File.separator + "processed_images");
	}
}