package org.csv.viewer.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.csv.viewer.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DownloadFilesController {

	private PdfService pdfService;

	@Autowired
	public DownloadFilesController(PdfService pdfService) {
		this.pdfService = pdfService;
	}

	private final static Logger logger = Logger.getLogger(DownloadFilesController.class);

	@RequestMapping(value = "download-old", method = RequestMethod.GET)
	public void downloadFile(HttpServletResponse response) {
		logger.debug("downloadFile()");

		try {

			InputStream is = new FileInputStream("C:\\Users\\makuz\\Desktop\\cv.pdf");

			IOUtils.copy(is, response.getOutputStream());

			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment; filename=somefile.pdf");
			response.setContentLength(12345678);
			response.flushBuffer();
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "download", method = RequestMethod.GET)
	public HttpEntity<byte[]> downloadPdfFile(@RequestParam("base64file") String base64CSV) {

		logger.debug("downloadPdfFile()");

		HttpEntity<byte[]> httpEntity = null;

		HttpHeaders headers = new HttpHeaders();
		String fileContent = Base64.getDecoder().decode(base64CSV).toString();
		byte[] documentBody = pdfService.createPdfFile();
		headers.setContentType(new MediaType("application", "pdf"));
		headers.set("Content-Disposition", "attachment; filename=somefile.pdf");
		headers.setContentLength(documentBody.length);
		httpEntity = new HttpEntity<byte[]>(documentBody, headers);

		return httpEntity;

	}

}
