package org.fasttrackit;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.ServletContextResource;
@Controller
public class ImageController {
	
	@Autowired
	ServletContext context;
	@RequestMapping(value = "img/{imagePath:.+}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Resource> getImageAsResource(@PathVariable String imagePath) {
	    HttpHeaders headers = new HttpHeaders();
	    Resource resource = 
	      new ServletContextResource(context, "/WEB-INF/images/" + imagePath);
	    return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}

}
