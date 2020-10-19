package com.example.Recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Recipe.services.RecipeService;

@Controller
public class IngredientController {

	RecipeService recipeService;

	public IngredientController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}
	
	@GetMapping
	@RequestMapping("/recipe/ingredients/{recipeId}")
	public String getIngredientById(@PathVariable String recipeId, Model model) {
		model.addAttribute("recipe", recipeService.getRecipeCommandById(Long.valueOf(recipeId)));
		return "rrecipes/ingredient/list";
	}
}
