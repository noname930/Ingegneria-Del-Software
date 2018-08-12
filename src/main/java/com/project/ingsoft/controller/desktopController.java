package com.project.ingsoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class desktopController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView home()
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home.html");
		return mav;
	}
}
