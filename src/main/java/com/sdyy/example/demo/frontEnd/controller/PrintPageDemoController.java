package com.sdyy.example.demo.frontEnd.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/printPage")
public class PrintPageDemoController {
	
	//private Logger logger = Logger.getLogger(this.getClass().getName());

	@RequestMapping("forExample")
	public String forExample(HttpServletRequest request){
		
		return "example/frontEnd/printPage/frontEnd_printPage_forExample";
	}      
}
