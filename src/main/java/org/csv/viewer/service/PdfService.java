package org.csv.viewer.service;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfStream;

@Service
public class PdfService {

	public void createPdfFile() {

		Document document = new Document();

		try {

			byte[] byteArr = null;
			PdfStream pdfStream = new PdfStream(byteArr);
			document.open();
			document.add(new Paragraph("A Hello World PDF document."));
			document.close();

		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

}
