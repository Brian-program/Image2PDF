package imageToPdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.property.AreaBreakType;

import java.util.ArrayList;
import java.util.Scanner;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

public class imageToPdf {

	
	public static void main(String[] args) {
		
		ArrayList<String> imagePaths = new ArrayList<String>();
		Scanner in = new Scanner(System.in);
		
		try {
			System.out.println("Input pdf filepath (Ex. C:\\\\Users\\\\Coder\\\\Pdf\\\\imageToPdf.pdf)");
			String documentPath = in.nextLine();
			System.out.println("Input all image filepaths (enter 'q' to stop input)");
			String imagePath = in.nextLine();
			while(!imagePath.equals("q")) {
				imagePaths.add(imagePath);
				System.out.println("Input all image filepaths (enter 'q' to stop input)");
				imagePath = in.nextLine();
			}
			
			imageToPdf file = new imageToPdf(documentPath, imagePaths);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public imageToPdf(String documentPath, ArrayList<String> imagePaths) {
		try {
			
			//create document from documentPath
			PdfWriter pdfWriter = new PdfWriter(documentPath);
			PdfDocument pdfDocument = new PdfDocument(pdfWriter);
			Document document = new Document(pdfDocument);
			
			for(String imagePath : imagePaths) {
				
			
				//create image from imagePath
				ImageData data = ImageDataFactory.create(imagePath);
				Image image = new Image(data);
				image.setAutoScale(true);
				
				//add image to cell
				Cell cellWithImage = new Cell();
				cellWithImage.add(image);
				
				//add cell to document with image in cell
				if(!imagePath.equals(imagePaths.get(0))) {
					document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
				}
				document.add(cellWithImage);
			}
			document.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
