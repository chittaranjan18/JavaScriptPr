package com.flight.utilities;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PDFgenerator {
	
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

	private static void addMetaData(Document document) {
		document.addTitle("My first PDF");
		document.addSubject("Using itext");
	    document.addKeywords("java,pDF,itext");
	    document.addAuthor("STARK");
	    document.addCreator("krishna");
	}
	private static void addTitleAndTable(Document document,String name,String email,String phone,String operatingAirlines, String dateOfDeparture,String departureCity,String arrivalCity )
			throws DocumentException{
		   Paragraph preface =new Paragraph();
		   preface.add(new Paragraph("Flight Booking Details",catFont));
		   preface.add(new Paragraph("Report generated by: " + "Flight Reservation Company "+new Date(),smallBold));
		   		
				   document.add(preface);
				   document.add(Chunk.NEWLINE);
				   document.add(Chunk.NEWLINE);
				   
				   PdfPTable table=new PdfPTable(2);
				   table.setWidthPercentage(100);
				   
				   PdfPCell c1=new PdfPCell(new Phrase("Passenger Detail"));
				   c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				   c1.setColspan(2);
				   table.addCell(c1);
				   table.addCell("passenger Details");
				   table.addCell(name);
				   table.addCell("Email Id");
				   table.addCell("phone number");
				   table.addCell(phone);
				   
				   document.add(table);
				   
				   PdfPTable table1=new PdfPTable(2);
				   table.setWidthPercentage(100);
				   PdfPCell c2=new PdfPCell(new Phrase("Flight Detail"));
				   c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				   c2.setColspan(2);
				   table.addCell(c2);
				   
				   table.addCell("operating Airlines");
				   table.addCell(operatingAirlines);
				   table.addCell("Departure Date");
				   table.addCell(dateOfDeparture);
				   table.addCell("Departure City ");
				   table.addCell(departureCity);
				   table.addCell("Arrival City");
				   table.addCell(arrivalCity);
				   
				   document.add(table1);
				   document.close();
				   
				   
		 
	}
	 public void generatePDF(String filePath,String name,String emailId,String phone,String operatingAirlines,Date dateOfDeparture,String departureCity,String arrivalCity ) {
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateOfDeparture1 = dateFormat.format(dateOfDeparture);
			Document document=new Document();
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();
			addMetaData(document);
			addTitleAndTable(document,name,emailId,phone,operatingAirlines,dateOfDeparture1,departureCity,arrivalCity);
			document.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}