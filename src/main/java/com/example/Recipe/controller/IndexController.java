package com.example.Recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping({"","/","/index"})
	public String getResult() {
		System.out.println("hi..");
		return "indexx";
	}
}
