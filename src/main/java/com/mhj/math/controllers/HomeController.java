package com.mhj.math.controllers;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("/")
	@Cacheable(value = "produtosHome")
	public ModelAndView index() {
		return new ModelAndView("home");
	}

}
