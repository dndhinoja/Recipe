package com.example.Recipe.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.Recipe.Domain.Ingredient;
import com.example.Recipe.commands.IngredientCommand;

public interface IngredientService{
	
	public IngredientCommand getIngredientByIdAndRecipeId(Long recipeId, Long ingredientId);
	
	public IngredientCommand saveOrUpdateIngredientCommand(IngredientCommand ingredientCommand);
}
