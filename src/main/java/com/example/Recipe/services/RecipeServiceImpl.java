package com.example.Recipe.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.Recipe.Domain.Recipe;
import com.example.Recipe.commands.RecipeCommand;
import com.example.Recipe.converters.RecipeCommandToRecipe;
import com.example.Recipe.converters.RecipeToRecipeCommand;
import com.example.Recipe.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	private final RecipeCommandToRecipe recipeCommandToRecipe;
	private final RecipeToRecipeCommand recipeToRecipeCommand;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe,
			RecipeToRecipeCommand recipeToRecipeCommand) {
		super();
		this.recipeRepository = recipeRepository;
		this.recipeCommandToRecipe = recipeCommandToRecipe;
		this.recipeToRecipeCommand = recipeToRecipeCommand;
	}

	@Override
	public Set<Recipe> getRecipe() {
		log.debug("I m in Sevice");
		Set<Recipe> setOfRecipe = new HashSet<Recipe>();
		recipeRepository.findAll().iterator().forEachRemaining(setOfRecipe::add);
		return setOfRecipe;
	}

	public Recipe getRecipeById(long l) {
		Optional<Recipe> recipe = recipeRepository.findById(l);
		
		if(!recipe.isPresent()) {
			throw new RuntimeException("Recipe not found");
		}
		return recipe.get();
	}
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
		Recipe recipe = recipeCommandToRecipe.convert(recipeCommand);
		
		Recipe savedRecipe = recipeRepository.save(recipe);
		RecipeCommand recipeCommand1  = recipeToRecipeCommand.convert(savedRecipe);
		return recipeCommand1;
		
	}

}
