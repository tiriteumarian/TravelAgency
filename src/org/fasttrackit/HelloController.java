package org.fasttrackit;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	


	@RequestMapping(value = "home.htm", method = RequestMethod.GET)
	public ModelAndView getHome() {
		ModelMap map = new ModelMap();
		map.put("name", "Marian");
		map.put("time", (new Date()).toString());

		return new ModelAndView("home", "model", map);
	}
	

}
