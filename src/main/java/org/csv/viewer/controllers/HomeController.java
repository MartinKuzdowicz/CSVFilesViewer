package org.csv.viewer.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private final static Logger logger = Logger.getLogger(HomeController.class);

	@RequestMapping("/home")
	public ModelAndView startPage() {
		logger.debug("home");

		ModelAndView mav = new ModelAndView("ViewerMain");

		return mav;

	}

}
