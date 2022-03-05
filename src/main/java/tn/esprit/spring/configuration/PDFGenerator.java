package tn.esprit.spring.configuration;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tn.esprit.spring.entities.Invitation;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFGenerator {
	
	private static Logger logger = LoggerFactory.getLogger(PDFGenerator.class);
	
	public static ByteArrayInputStream customerPDFReport(List<Invitation> invitations) {
		Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
        	
        	PdfWriter.getInstance(document, out);
            document.open();
        	
			// Add Text to PDF file ->
			Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLACK);
			Paragraph para = new Paragraph( "Invitation Table", font);
			para.setAlignment(Element.ALIGN_CENTER);
			document.add(para);
			document.add(Chunk.NEWLINE);
        	
        	PdfPTable table = new PdfPTable(5);
        	// Add PDF Table Header ->
			Stream.of("idInvitation", "contenu", "codeAcces","typeInvitation","invitationStatus")
			    .forEach(headerTitle -> {
			          PdfPCell header = new PdfPCell();
			          Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			          header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			          header.setHorizontalAlignment(Element.ALIGN_CENTER);
			          header.setBorderWidth(2);
			          header.setPhrase(new Phrase(headerTitle, headFont));
			          table.addCell(header);
			    });
            
            for (Invitation invitation : invitations) {
            	PdfPCell idInvitCell = new PdfPCell(new Phrase(Long.toString(invitation.getIdInvitation())));
            	idInvitCell.setPaddingLeft(4);
            	idInvitCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            	idInvitCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idInvitCell);

                PdfPCell contenuCell = new PdfPCell(new Phrase(invitation.getContenu()));
                contenuCell.setPaddingLeft(4);
                contenuCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                contenuCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(contenuCell);

                PdfPCell codeAccessCell = new PdfPCell(new Phrase(String.valueOf(invitation.getCodeAcces())));
                codeAccessCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                codeAccessCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                codeAccessCell.setPaddingRight(4);
                table.addCell(codeAccessCell);
                
                PdfPCell typeInvitationCell = new PdfPCell(new Phrase(String.valueOf(invitation.getTypeInvitation())));
                typeInvitationCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                typeInvitationCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                typeInvitationCell.setPaddingRight(4);
                table.addCell(typeInvitationCell);
                
                PdfPCell invitationStatusCell = new PdfPCell(new Phrase(String.valueOf(invitation.getInvitationStatus())));
                invitationStatusCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                invitationStatusCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                invitationStatusCell.setPaddingRight(4);
                table.addCell(invitationStatusCell);
            }
            document.add(table);
            
            document.close();
        }catch(DocumentException e) {
        	logger.error(e.toString());
        }
        
		return new ByteArrayInputStream(out.toByteArray());
	}
}