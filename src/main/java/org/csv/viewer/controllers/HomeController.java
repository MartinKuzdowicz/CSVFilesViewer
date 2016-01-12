package org.csv.viewer.controllers;

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
	public ModelAndView uploadCsv(@RequestParam("csvFile") MultipartFile file) {
		logger.debug("uploadCsv");

		System.out.println(file.getOriginalFilename());
		System.out.println(file);

		ModelAndView mav = new ModelAndView("ViewerMain");

		return mav;

	}

}
