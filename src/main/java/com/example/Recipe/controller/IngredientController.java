package com.example.Recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Recipe.commands.IngredientCommand;
import com.example.Recipe.commands.UnitOfMeasureCommand;
import com.example.Recipe.services.IngredientService;
import com.example.Recipe.services.RecipeService;
import com.example.Recipe.services.UnitOfMeasureService;

@Controller
public class IngredientController {

	RecipeService recipeService;
	IngredientService ingredientService;
	UnitOfMeasureService unitOfMeasureSerive;

	public IngredientController(RecipeService recipeService, IngredientService ingredientService,
			UnitOfMeasureService unitOfMeasureService) {
		super();
		this.recipeService = recipeService;
		this.ingredientService = ingredientService;
		this.unitOfMeasureSerive = unitOfMeasureService;
	}

	@GetMapping
	@RequestMapping("/recipe/ingredients/{recipeId}")
	public String getIngredientById(@PathVariable String recipeId, Model model) {
		model.addAttribute("recipe", recipeService.getRecipeCommandById(Long.valueOf(recipeId)));
		return "rrecipes/ingredient/list";
	}
	
	@GetMapping
	@RequestMapping("/recipe/ingredient/{recipeId}/show/{ingredientId}")
	public String getIngredientByIdAndRecipeId(@PathVariable String recipeId,@PathVariable String ingredientId, Model model) {
		model.addAttribute("ingredient", ingredientService.getIngredientByIdAndRecipeId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
		return "rrecipes/ingredient/show";
	}
	
	@GetMapping
	@RequestMapping("/recipe/ingredient/{recipeId}/update/{ingredientId}")
	public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
		model.addAttribute("ingredient", ingredientService.getIngredientByIdAndRecipeId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
		model.addAttribute("uomList", unitOfMeasureSerive.getUomList());
		return "rrecipes/ingredient/ingredientform";
	}
	
	@PostMapping
	@RequestMapping("/recipe/{recipeId}/ingredient")
	public String saveOrUpdateIngredient(@ModelAttribute IngredientCommand ingredientCommand) {
		IngredientCommand savedIngredientCommand = ingredientService.saveOrUpdateIngredientCommand(ingredientCommand);
		return "redirect:/recipe/ingredient/"+savedIngredientCommand.getRecipeId()+"/show/"+savedIngredientCommand.getId();
	}
	
	@GetMapping
	@RequestMapping("/recipe/ingredient/{recipeId}/new")
	public String addIngredient(@PathVariable String recipeId, Model model) {
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setRecipeId(Long.valueOf(recipeId));
		model.addAttribute("ingredient", ingredientCommand);
		ingredientCommand.setUnitOfMeasureCommand(new UnitOfMeasureCommand());
		model.addAttribute("uomList", unitOfMeasureSerive.getUomList());
		
		return "rrecipes/ingredient/ingredientform";
	}
	
	@DeleteMapping
	@PostMapping
	@RequestMapping(value = "/recipe/ingredient/{recipeId}/delete/{ingredientId}")
	public String deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId) {
		
		ingredientService.deleteIngredientByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));
		
		return "redirect:/recipe/ingredients/" + recipeId;
	}
}
