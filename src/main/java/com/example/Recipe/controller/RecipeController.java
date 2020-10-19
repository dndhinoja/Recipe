package com.example.Recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	//done
	@RequestMapping("/recipes")
	public String getResult(Model model) {
		log.info("Hi I am in Controller");
		model.addAttribute("recipes",recipeService.getRecipe());
		return "rrecipes/index";
	}
	//done
	@RequestMapping("/recipe/show/{id}")
	public String GetRecipeByIdController(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.getRecipeById(new Long(id)));
		return "Display/view";
	}
	//done
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
	
	@GetMapping
	@RequestMapping("/recipe/update/{id}")
	public String update(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.getRecipeCommandById(Long.valueOf(id)));
		return "rrecipes/addrecipe";
	}
	
	@GetMapping
	@RequestMapping("/recipe/delete/{id}")
	public String delete(@PathVariable String id) {
		recipeService.deleteRecipeById(Long.valueOf(id));
		return "redirect:/recipes";
	}
}
