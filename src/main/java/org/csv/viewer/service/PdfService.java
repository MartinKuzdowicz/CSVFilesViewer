package org.csv.viewer.service;

import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfService {

	public byte[] createPdfFile() {

		Document document = new Document();
		ByteArrayOutputStream ostream = new ByteArrayOutputStream();

		try {

			PdfWriter.getInstance(document, ostream);
			document.open();
			document.add(new Paragraph("A Hello World PDF document."));
			document.close();

		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return ostream.toByteArray();

	}

}
