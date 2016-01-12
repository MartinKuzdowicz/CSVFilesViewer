package org.csv.viewer.controllers;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private final static Logger logger = Logger.getLogger(HomeController.class);

	@RequestMapping("/view-csv")
	public ModelAndView startPage() {
		logger.debug("home");

		ModelAndView mav = new ModelAndView("ViewerMain");

		return mav;

	}

	@RequestMapping(value = "/view-csv", method = RequestMethod.POST)
	public ModelAndView uploadCsv(@RequestParam("csvFile") MultipartFile csvFile) {
		logger.debug("uploadCsv");

		String fileContent = null;
		StringBuilder sb = new StringBuilder();

		if (!csvFile.isEmpty()) {

			try {
				InputStream is = csvFile.getInputStream();

				int data;

				while ((data = is.read()) != -1) {

					sb.append((char) data);

				}

				fileContent = sb.toString();

			} catch (IOException e) {
				logger.debug(e);
			}

		}

		ModelAndView mav = new ModelAndView("ViewerMain");
		mav.addObject("fileContent", fileContent);

		return mav;

	}

}
