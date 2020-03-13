package com.aplication.proyecto.view.pdf;

import java.awt.Color;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Phaser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.aplication.proyecto.entity.Persona;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import sun.security.jca.GetInstance;

@Component("persona/ver")
public class PersonaPdf extends AbstractPdfView{
	Log log=LogFactory.getLog("PersonaPdf");
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		log.info("jajaj no funcion pdf"+model.get("persona"));
		
		PdfPTable tabla=new PdfPTable(5);
		tabla.setWidths(new float[] {1,2,2,2,4});
		
		PdfPCell celId = new PdfPCell(new Phrase("ID"));
		celId.setBackgroundColor(new Color(67,255,0));
		celId.setPadding(10);
		tabla.addCell("id");
		tabla.addCell("Nombres");
		tabla.addCell("Apellidos");
		tabla.addCell("CI");
		tabla.addCell("Gmail");
		
		Persona p;
		p=(Persona)model.get("persona");
		
		tabla.addCell(String.valueOf( p.getId()));
		tabla.addCell(String.valueOf( p.getNombres()));
		tabla.addCell(String.valueOf( p.getApellidos()));
		tabla.addCell(String.valueOf( p.getCi()));
		tabla.addCell(String.valueOf( p.getEmail()));	
		
		Path pathFoto=Paths.get("uploads").resolve(p.getFoto()).toAbsolutePath();
		log.info("path::::"+pathFoto);
		//log.info("C:\\Users\\DELL\\Documents\\workspace-spring-tool-suite\\proyecto\\uploads\\"+p.getFoto().toString());
		Image img=Image.getInstance(pathFoto.toString());
		
		document.add(img);
		document.add(tabla);
		document.add(new Paragraph("Hello world"));
		document.add(new Paragraph("Hello world"));
		document.add(new Paragraph("Hello world"));		
	}
	
}
