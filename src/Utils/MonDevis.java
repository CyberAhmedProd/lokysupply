package Utils;

import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import models.Devis;
import services.DevisServiceImpl;

public class MonDevis {
	int idDevis;

	public MonDevis(int idDevis) {
		super();
		this.idDevis = idDevis;
		com.itextpdf.text.Font blueFont = FontFactory.getFont(FontFactory.HELVETICA, 50, Font.BOLD, new CMYKColor(255, 10, 0, 0));
		com.itextpdf.text.Font blueFontName = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(255, 10, 0, 0));
		com.itextpdf.text.Font blackFontName = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new CMYKColor(255, 10, 0, 0));
		com.itextpdf.text.Font blueFontNormal = FontFactory.getFont(FontFactory.HELVETICA, 10, com.itextpdf.text.Font.NORMAL, new CMYKColor(255, 0, 0, 0));
		com.itextpdf.text.Font blackFontNormal = FontFactory.getFont(FontFactory.HELVETICA, 8, com.itextpdf.text.Font.BOLD, new CMYKColor(255,255, 255, 0));
		com.itextpdf.text.Font redNormal = FontFactory.getFont(FontFactory.HELVETICA, 10, com.itextpdf.text.Font.BOLD, new CMYKColor(0,0, 255, 0));
		
		
		
		//info depuis la base
		
		DevisServiceImpl devisService = new DevisServiceImpl();
		DecimalFormat df = new DecimalFormat("#.##");
		Devis devis = devisService.getDevis(idDevis);
		int totalHT = 0;
		int totalTax = 0;
		
		//
		
		
		
		
		
		Document document = new Document();
	      try
	      {
	         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("devis.pdf"));
	         document.open();
	         // Etage 1
	         PdfPTable table = new PdfPTable(2); // 2 columns.
	         table.setSpacingBefore(10f); //Space before table
	         table.setSpacingAfter(10f); //Space after table
	    
	         
	        
	         PdfPCell cell1 = new PdfPCell();
	         Paragraph paragraphDevis = new Paragraph("Devis",blueFont);
	         cell1.addElement(paragraphDevis);
	         Paragraph paragraphNom = new Paragraph("Name", blueFontName);
	         cell1.addElement(paragraphNom);
	         Paragraph paragraphNumAndRue = new Paragraph("Num , Rue", blueFontNormal);
	         cell1.addElement(paragraphNumAndRue);
	         Paragraph paragraphCodeVille = new Paragraph("code , ville", blueFontNormal);
	         cell1.addElement(paragraphCodeVille);
	         
	         cell1.setRowspan(4);
	         cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell1.setBorder(Rectangle.NO_BORDER);
	         
	         
	         
	         
	         
	       //Add Image
	         Image image1 = Image.getInstance("hobbitre.png");
	         //Fixed Positioning
	         image1.setAbsolutePosition(100f, 550f);
	         //Scale to new height and new width of image
	         image1.scaleAbsolute(100, 100);
	         //Add to document
	         PdfPCell cell2 = new PdfPCell(image1);
	         cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
	         cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell2.setBorder(Rectangle.NO_BORDER);
	         table.addCell(cell1);
	         table.addCell(cell2);
	         
	         document.add(table);
	      // Etage 1 fin
	         
	      // Etage 2
	         
	         PdfPTable table2 = new PdfPTable(4); // 4 columns.
	         table2.setSpacingBefore(10f); //Space before table
	         table2.setSpacingAfter(10f); //Space after table
	    
	         
	        
	        /* PdfPCell cell21 = new PdfPCell();
	         Paragraph paragraphFacure = new Paragraph("FACTURE A",blueFontName);
	         cell21.addElement(paragraphFacure);
	         Paragraph paragraphNomClient = new Paragraph("Name", blueFontName);
	         cell21.addElement(paragraphNomClient);
	         Paragraph paragraphNumAndRueClient = new Paragraph("Num , Rue", blueFontName);
	         cell21.addElement(paragraphNumAndRueClient);
	         Paragraph paragraphCodeVilleClient = new Paragraph("code , ville", blueFontName);
	         cell21.addElement(paragraphCodeVilleClient);
	         
	         cell21.setRowspan(4);
	         cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell21.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell21.setBorder(Rectangle.NO_BORDER);*/
	         
	         /////////////////////
	         PdfPCell cell22 = new PdfPCell();
	         Paragraph paragraphFacureE = new Paragraph("ENVOYE A",blueFontName);
	         cell22.addElement(paragraphFacureE);
	         Paragraph paragraphNomClientE = new Paragraph(devis.getClient().getRaisonSocial().getNom()+" "+devis.getClient().getRaisonSocial().getPrenom(), blueFontNormal);
	         cell22.addElement(paragraphNomClientE);
	         Paragraph paragraphNumAndRueClientE = new Paragraph(Integer.toString(devis.getClient().getAdresse().getNumRue())+","+devis.getClient().getAdresse().getLibelleRue(), blueFontNormal);
	         cell22.addElement(paragraphNumAndRueClientE);
	         Paragraph paragraphCodeVilleClientE = new Paragraph(Integer.toString(devis.getClient().getAdresse().getCodePostale())+","+devis.getClient().getAdresse().getNomVille(), blueFontNormal);
	         cell22.addElement(paragraphCodeVilleClientE);
	         cell22.setColspan(2);
	         cell22.setRowspan(4);
	         cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell22.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell22.setBorder(Rectangle.NO_BORDER);
	         
	         
	         ///////////////////////////
	         PdfPCell cell23 = new PdfPCell();
	         Paragraph paragraphD = new Paragraph("DEVIS N",blueFontName);
	         cell23.addElement(paragraphD);
	         Paragraph paragraphDateDevis = new Paragraph("Date", blueFontName);
	         cell23.addElement(paragraphDateDevis);
	         /*Paragraph paragraphCommande = new Paragraph("COMMANDE N", blueFontName);
	         cell23.addElement(paragraphCommande);*/
	       
	         
	         cell23.setRowspan(2);
	         cell23.setHorizontalAlignment(Element.ALIGN_RIGHT);
	         cell23.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell23.setBorder(Rectangle.NO_BORDER);
	         
	         ///////////////////////////
	         PdfPCell cell24 = new PdfPCell();
	         Paragraph paragraphDnumber = new Paragraph(devis.getCode(),blueFontNormal);
	         cell24.addElement(paragraphDnumber);
	         SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYY, HH:mm");
	         Paragraph paragraphDate = new Paragraph(dateFormat.format(devis.getDate()), blueFontNormal);
	         cell24.addElement(paragraphDate);
	         //Paragraph paragraphCommandeNum = new Paragraph("54879644", blueFontNormal);
	         //cell24.addElement(paragraphCommandeNum);
	       
	         
	         cell24.setRowspan(2);
	         cell24.setHorizontalAlignment(Element.ALIGN_RIGHT);
	         cell24.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell24.setBorder(Rectangle.NO_BORDER);
	         
	         
	         ///////////////
	         
	         //table2.addCell(cell21);
	         table2.addCell(cell22);
	         table2.addCell(cell23);
	         table2.addCell(cell24);
	         document.add(table2);
	         
	      // Etage 2 fin
	         document.add(new Paragraph("\n"));
	      // Etage 3
	         
	         
	         PdfPTable table3 = new PdfPTable(4); // 4 columns.
	         table3.setSpacingBefore(8f); //Space before table
	         table3.setSpacingAfter(8f); //Space after table
	    
	         
	        
	         PdfPCell cell31 = new PdfPCell();
	         Paragraph paragraphQTE = new Paragraph("QTE",blueFontName);
	         cell31.addElement(paragraphQTE);
	    
	         cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell31.setVerticalAlignment(Element.ALIGN_MIDDLE);
	       
	         cell31.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
	         cell31.setBorderColor(BaseColor.RED);
	         cell31.setPadding(10);
	         
	         /////////////////////
	         PdfPCell cell32 = new PdfPCell();
	         Paragraph paragraphDesignation = new Paragraph("DESIGNATION",blueFontName);
	         cell32.addElement(paragraphDesignation);
	       
	         cell32.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell32.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell32.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
	         cell32.setBorderColor(BaseColor.RED);
	         
	         cell32.setPaddingBottom(4);
	         
	         ///////////////////////////
	         PdfPCell cell33 = new PdfPCell();
	         Paragraph paragraphPriceht = new Paragraph("Prix UNT HT",blueFontName);
	         cell33.addElement(paragraphPriceht);
	        
	         cell33.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell33.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      
	         cell33.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
	         cell33.setBorderColor(BaseColor.RED);
	         cell33.setPaddingBottom(4);
	         ///////////////////////////
	         PdfPCell cell34 = new PdfPCell();
	         Paragraph paragraphMantantht = new Paragraph("Montant HT",blueFontName);
	         cell34.addElement(paragraphMantantht);
	        
	         cell34.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell34.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         cell34.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
	         cell34.setBorderColor(BaseColor.RED);
	         cell34.setPaddingBottom(4);
	         
	         ///////////////*
	         
	         
	         table3.addCell(cell31);
	         table3.addCell(cell32);
	         table3.addCell(cell33);
	         table3.addCell(cell34);
	         document.add(table3);
	         
	      // Etage 3 fin
	         
	         
	        // Etage 4
	         
	         
	       
	         for(int i=0;i<devisService.getAllLigneDevis(idDevis).size();i++) {
	        	 	
	        	  	 PdfPTable table4 = new PdfPTable(4); // 4 columns.
			         table4.setSpacingBefore(10f); //Space before table
			         table4.setSpacingAfter(10f); //Space after table
	        	 
	        	   	 PdfPCell cell41 = new PdfPCell();
	        	   		
			         Paragraph idp = new Paragraph(Integer.toString(devisService.getAllLigneDevis(idDevis).get(i).getQuantity()),blackFontNormal);
			         cell41.addElement(idp);
			         cell41.setHorizontalAlignment(Element.ALIGN_LEFT);
			         cell41.setVerticalAlignment(Element.ALIGN_MIDDLE);
			         cell41.setBorder(Rectangle.NO_BORDER);
			         
			         
			         
			         /////////////////////
			         PdfPCell cell42 = new PdfPCell();
			         Paragraph pDesignation = new Paragraph(devisService.getAllLigneDevis(idDevis).get(i).getProducts().getDesignation(),blackFontNormal);
			         cell42.addElement(pDesignation);
			        
			         cell42.setHorizontalAlignment(Element.ALIGN_LEFT);
			         cell42.setVerticalAlignment(Element.ALIGN_MIDDLE);
			         cell42.setBorder(Rectangle.NO_BORDER);
			         
			         
			         ///////////////////////////
			         PdfPCell cell43 = new PdfPCell();
			         Paragraph pPriceht = new Paragraph(Double.toString(devisService.getAllLigneDevis(idDevis).get(i).getProducts().getUnitPriceHt()),blackFontNormal);
			         cell43.addElement(pPriceht);
			        
			         cell43.setHorizontalAlignment(Element.ALIGN_LEFT);
			         cell43.setVerticalAlignment(Element.ALIGN_MIDDLE);
			         cell43.setBorder(Rectangle.NO_BORDER);
			         
			         ///////////////////////////
			         PdfPCell cell44 = new PdfPCell();
			         Paragraph pMantantht = new Paragraph(Double.toString(devisService.getAllLigneDevis(idDevis).get(i).getTotalHt()),blackFontNormal);
			         cell44.addElement(pMantantht);
			        
			         cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
			         cell44.setVerticalAlignment(Element.ALIGN_MIDDLE);
			         cell44.setBorder(Rectangle.NO_BORDER);
			       
			         
			         ///////////////
			         
			         table4.addCell(cell41);
			         table4.addCell(cell42);
			         table4.addCell(cell43);
			         table4.addCell(cell44);
			         document.add(table4);
			         totalHT+=devisService.getAllLigneDevis(idDevis).get(i).getTotalHt();
			         totalTax+= devisService.getAllLigneDevis(idDevis).get(i).getTotalTva();
			         
	         }
	         
	        
	   
	       
	         
	      // Etage 4fin
	         
	       // etage 5
	         

	         
	         
	         PdfPTable table5 = new PdfPTable(4); // 4 columns.
	         table5.setSpacingBefore(8f); //Space before table
	         table5.setSpacingAfter(8f); //Space after table
	    
	         
	        
	         PdfPCell cell51 = new PdfPCell();
	         Paragraph paragraphOneVide = new Paragraph("",blueFontName);
	         cell51.addElement(paragraphOneVide);
	    
	         cell51.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell51.setVerticalAlignment(Element.ALIGN_MIDDLE);
	       
	         cell51.setBorder(Rectangle.NO_BORDER);
	         
	         
	         /////////////////////
	         PdfPCell cell52 = new PdfPCell();
	         Paragraph paragraphdeuxVide = new Paragraph("",blueFontName);
	         cell52.addElement(paragraphdeuxVide);
	       
	         cell52.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell52.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell52.setBorder(Rectangle.NO_BORDER);

	         
	         ///////////////////////////
	         PdfPCell cell53 = new PdfPCell();
	         Paragraph paragraphTotalht = new Paragraph("Total Ht",blackFontName);
	         cell53.addElement(paragraphTotalht);
	        
	         cell53.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell53.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      
	         cell53.setBorder(Rectangle.NO_BORDER);

	         ///////////////////////////
	         PdfPCell cell54 = new PdfPCell();
	      
	         Paragraph paragraphMantanHt = new Paragraph(df.format(totalHT),blackFontName);
	         cell54.addElement(paragraphMantanHt);
	        
	         cell54.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell54.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         cell54.setBorder(Rectangle.NO_BORDER);
	    
	         ///////////////*
	         
	         
	         table5.addCell(cell51);
	         table5.addCell(cell52);
	         table5.addCell(cell53);
	         table5.addCell(cell54);
	         document.add(table5);
	         
	
	         
	         
	         // fin etage 5
	         
	         
 // etage 6
	         

	         
	         
	         PdfPTable table6 = new PdfPTable(4); // 4 columns.
	         table6.setSpacingBefore(8f); //Space before table
	         table6.setSpacingAfter(8f); //Space after table
	    
	         
	        
	         PdfPCell cell61 = new PdfPCell();
	         Paragraph paragraphOneeVide = new Paragraph("",blueFontName);
	         cell61.addElement(paragraphOneeVide);
	    
	         cell61.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell61.setVerticalAlignment(Element.ALIGN_MIDDLE);
	       
	         cell61.setBorder(Rectangle.NO_BORDER);
	    
	         
	         /////////////////////
	         PdfPCell cell62 = new PdfPCell();
	         Paragraph paragraphdeeuxVide = new Paragraph("",blueFontName);
	         cell62.addElement(paragraphdeeuxVide);
	       
	         cell62.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell62.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell62.setBorder(Rectangle.NO_BORDER);
	     
	         
	         ///////////////////////////
	         PdfPCell cell63 = new PdfPCell();
	         Paragraph paragraphTVA = new Paragraph("TVA 18%",blackFontName);
	         cell63.addElement(paragraphTVA);
	        
	         cell63.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell63.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      
	         cell63.setBorder(Rectangle.NO_BORDER);
	  
	         ///////////////////////////
	         PdfPCell cell64 = new PdfPCell();
	         Paragraph paragraphMantanTVA = new Paragraph(df.format(totalHT*0.18),blackFontName);
	         cell64.addElement(paragraphMantanTVA);
	        
	         cell64.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell64.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         cell64.setBorder(Rectangle.NO_BORDER);
	      
	         
	         ///////////////*
	         
	         
	         table6.addCell(cell61);
	         table6.addCell(cell62);
	         table6.addCell(cell63);
	         table6.addCell(cell64);
	         document.add(table6);
	         
	
	         
	         
	         // fin etage 6
	         
	         
 // etage 7
	         

	         
	         
	         PdfPTable table7 = new PdfPTable(4); // 4 columns.
	         table7.setSpacingBefore(8f); //Space before table
	         table7.setSpacingAfter(8f); //Space after table
	    
	         
	        
	         PdfPCell cell71 = new PdfPCell();
	         Paragraph paragraphOoneeVide = new Paragraph("",blackFontName);
	         cell71.addElement(paragraphOoneeVide);
	    
	         cell71.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell71.setVerticalAlignment(Element.ALIGN_MIDDLE);
	       
	         cell71.setBorder(Rectangle.NO_BORDER);
	    
	         
	         /////////////////////
	         PdfPCell cell72 = new PdfPCell();
	         Paragraph paragraphdedeuxVide = new Paragraph("",blackFontName);
	         cell72.addElement(paragraphdedeuxVide);
	       
	         cell72.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell72.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         cell72.setBorder(Rectangle.NO_BORDER);
	     
	         
	         ///////////////////////////
	         PdfPCell cell73 = new PdfPCell();
	         Paragraph paragraphTotalTva = new Paragraph("TOTAL",redNormal);
	         cell73.addElement(paragraphTotalTva);
	        
	         cell73.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell73.setVerticalAlignment(Element.ALIGN_MIDDLE);
	      
	         cell73.setBorder(Rectangle.NO_BORDER);
	  
	         ///////////////////////////
	         PdfPCell cell74 = new PdfPCell();
	         Paragraph paragraphMantanAvecTVA = new Paragraph(df.format(totalTax),redNormal);
	         cell74.addElement(paragraphMantanAvecTVA);
	        
	         cell74.setHorizontalAlignment(Element.ALIGN_LEFT);
	         cell74.setVerticalAlignment(Element.ALIGN_MIDDLE);
	         
	         cell74.setBorder(Rectangle.NO_BORDER);
	      
	         
	         ///////////////*
	         
	         
	         table7.addCell(cell71);
	         table7.addCell(cell72);
	         table7.addCell(cell73);
	         table7.addCell(cell74);
	         document.add(table7);
	         
	
	         
	         
	         // fin etage 7
	        
	        
	         document.close();
	         writer.close();
	      } catch (DocumentException d)
	      {
	         d.printStackTrace();
	      } catch (FileNotFoundException d)
	      {
	         d.printStackTrace();
	      } catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
