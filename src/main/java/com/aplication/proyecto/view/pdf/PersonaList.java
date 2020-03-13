package com.aplication.proyecto.view.pdf;
import java.awt.Color;
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
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("persona/index")
public class PersonaList extends AbstractPdfView{
	Log log=LogFactory.getLog("PersonaList");
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		log.info("jajaj no funcion pdf"+model.get("personas"));
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
		//Object personas = null;
		model.get("personas");
		//Persona persona;
		//PdfPTable tabla3=new PdfPTable(5);
		List<Persona> p=new ArrayList<Persona>();
		p=(List<Persona>)model.get("personas");
		
		p.forEach(persona->
			{
			tabla.addCell(persona.getId()+"");			
			tabla.addCell(persona.getNombres());
			tabla.addCell(persona.getApellidos());
			tabla.addCell(persona.getCi()+"");
			tabla.addCell(persona.getEmail());
			
			});
		
		//log.info("---"+persona.getApellidos());
		//PdfPTable tabla=new PdfPTable(1);
		tabla.addCell("datos de las persona2");
		
		document.add(tabla);
		document.add(new Paragraph("Hello world"));
		document.add(new Paragraph("Hello world"));
		document.add(new Paragraph("Hello world"));
		//document.add(tabla3);
		//document.add(tabla2);
		
		
	}
	
}