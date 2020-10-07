package com.example.Recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Recipe.repositories.RecipeRepository;
import com.example.Recipe.services.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RecipeController {

	private RecipeService recipeService;

	
	public RecipeController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}


	@RequestMapping("/recipes")
	public String getResult(Model model) {
		log.info("Hi I am in Controller");
		model.addAttribute("recipes",recipeService.getRecipe());
		return "rrecipes/index";
	}
	
}
