package com.garbuz.messaging.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.garbuz.messaging.model.HomePage;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private Logger LOG = LoggerFactory.getLogger(HomeController.class);

	@GetMapping("/home")
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HomePage homePage = new HomePage();
		homePage.setTitle("Rabbit MQ Test");
		
		mv.addObject("homePage", homePage);
		mv.setViewName("homePage");
		LOG.debug("Returning {} page", homePage);
		return mv;
	}
	
	
}
