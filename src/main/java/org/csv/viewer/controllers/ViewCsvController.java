package org.csv.viewer.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Map;

import org.apache.commons.io.IOUtils;
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

	private final static Logger logger = Logger.getLogger(ViewCsvController.class);

	@RequestMapping("/view-csv")
	public ModelAndView startPage() {

		logger.debug("home page");

		ModelAndView mav = new ModelAndView("ViewerMain");
		addBasicObjects(mav);
		return mav;

	}

	@RequestMapping(value = "/view-csv", method = RequestMethod.POST)
	public ModelAndView uploadCsv(@RequestParam("csvFile") MultipartFile csvFile,
			@RequestParam Map<String, String> reqBodyMap) {

		logger.debug("uploadCsv");

		boolean withHeaderFlag = reqBodyMap.containsKey("withHeader") ? true : false;

		CsvTableDTO recordsTableDTO = null;
		byte[] base64Str = null;

		if (!csvFile.isEmpty()) {

			try {

				base64Str = Base64.getEncoder().encode(IOUtils.toByteArray(csvFile.getInputStream()));
				recordsTableDTO = csvParserService.parseISToDTO(csvFile.getInputStream(), withHeaderFlag);
			} catch (IOException e) {
				logger.debug(e);
			}

		}

		ModelAndView mav = new ModelAndView("ViewerMain");
		addBasicObjects(mav);

		mav.addObject("base64CSVFile", new String(base64Str));
		mav.addObject("recordsTableDTO", recordsTableDTO);
		mav.addObject("withHeaderFlag", withHeaderFlag);

		return mav;

	}

	public void addBasicObjects(ModelAndView mav) {

		LocalDate localeTime = LocalDate.now();

		mav.addObject("localeTime", localeTime);
	}

}
