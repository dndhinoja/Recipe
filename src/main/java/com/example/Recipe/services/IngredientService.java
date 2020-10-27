package com.example.Recipe.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Recipe.Domain.Ingredient;
import com.example.Recipe.commands.IngredientCommand;

@Component
public interface IngredientService{
	
	public IngredientCommand getIngredientByIdAndRecipeId(Long recipeId, Long ingredientId);
	
	public IngredientCommand saveOrUpdateIngredientCommand(IngredientCommand ingredientCommand);
	
	public void deleteIngredientByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
