package com.example.Recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Recipe.commands.RecipeCommand;
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
	
	@RequestMapping("/recipe/show/{id}")
	public String GetRecipeByIdController(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.getRecipeById(new Long(id)));
		return "Display/view";
	}
	
	@RequestMapping("/recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		return "rrecipes/addrecipe";
	}
	
	@PostMapping
	@RequestMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
		RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
		return "redirect:/recipe/show/" + savedRecipeCommand.getId();
	}
}
