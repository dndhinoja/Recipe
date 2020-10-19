package com.example.Recipe.services;

import java.util.Set;

import com.example.Recipe.Domain.Recipe;
import com.example.Recipe.commands.RecipeCommand;

public interface RecipeService {

	public Set<Recipe> getRecipe();

	public Recipe getRecipeById(long l);
	
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
	
	public RecipeCommand getRecipeCommandById(long l);
	
	public void deleteRecipeById(long l);
}
