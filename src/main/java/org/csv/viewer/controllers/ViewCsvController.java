package org.csv.viewer.controllers;

import java.io.IOException;
import java.time.LocalDate;

import org.apache.log4j.Logger;
import org.csv.viewer.parser.CsvParserService;
import org.csv.viewer.parser.CsvTableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewCsvController {

	@Autowired
	CsvParserService csvParserService;

	private final static Logger logger = Logger
			.getLogger(ViewCsvController.class);

	@RequestMapping("/view-csv")
	public ModelAndView startPage() {

		logger.debug("home page");

		ModelAndView mav = new ModelAndView("ViewerMain");
		addBasicObjects(mav);
		return mav;

	}

	@RequestMapping(value = "/view-csv", method = RequestMethod.POST)
	public ModelAndView uploadCsv(@RequestParam("csvFile") MultipartFile csvFile) {

		logger.debug("uploadCsv");

		CsvTableDTO recordsTableDTO = null;
		if (!csvFile.isEmpty()) {

			try {
				recordsTableDTO = csvParserService.parseISToDTO(csvFile
						.getInputStream());
			} catch (IOException e) {
				logger.debug(e);
			}

		}

		ModelAndView mav = new ModelAndView("ViewerMain");
		addBasicObjects(mav);

		mav.addObject("recordsTableDTO", recordsTableDTO);

		return mav;

	}

	public void addBasicObjects(ModelAndView mav) {

		LocalDate localeTime = LocalDate.now();

		mav.addObject("localeTime", localeTime);
	}

}
