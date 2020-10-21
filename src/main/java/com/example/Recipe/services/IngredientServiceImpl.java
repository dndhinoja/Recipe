package com.example.Recipe.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Recipe.Domain.Ingredient;
import com.example.Recipe.Domain.Recipe;
import com.example.Recipe.commands.IngredientCommand;
import com.example.Recipe.commands.RecipeCommand;
import com.example.Recipe.converters.IngredientCommandToIngredient;
import com.example.Recipe.converters.IngredientToIngredientCommand;
import com.example.Recipe.repositories.IngredientRepository;
import com.example.Recipe.repositories.RecipeRepository;
import com.example.Recipe.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	final RecipeRepository recipeRepository;
	final IngredientRepository ingredientRepository;
	final IngredientToIngredientCommand ingredientToIngredientCommand;
	final IngredientCommandToIngredient ingredientCommandToIngredient;
	final UnitOfMeasureRepository unitOfMeasureRepository;

	public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientRepository ingredientRepository,
			IngredientToIngredientCommand ingredientToIngredientCommand,
			IngredientCommandToIngredient ingredientCommandToIngredient,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.recipeRepository = recipeRepository;
		this.ingredientRepository = ingredientRepository;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public IngredientCommand getIngredientByIdAndRecipeId(Long recipeId, Long ingredientId) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

		if (!recipeOptional.isPresent()) {
			log.debug("not found Id"+recipeId);
		}
		Recipe recipe = recipeOptional.get();

		/*
		 * Set<Ingredient> ingredients = recipe.getSetOfIngredient(); Ingredient
		 * specificIngredient = new Ingredient(); for (Ingredient ingredient :
		 * ingredients) { if (ingredient.getId().equals(ingredientId)) {
		 * specificIngredient = ingredient; } }
		 */

		Optional<IngredientCommand> ingredientOptional = recipe.getSetOfIngredient().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
				.findFirst();

		/*
		 * if (!ingredientOptional.isPresent()) {
		 * System.out.println("Ingredient id not found" + ingredientId); }
		 * IngredientCommand in =
		 * ingredientToIngredientCommand.convert(ingredientOptional.get());
		 * 
		 * return in;
		 */
		if(!ingredientOptional.isPresent()) {
			log.debug("not found"+ingredientId);
		}
		return ingredientOptional.get();
	}
	
	@Transactional
	@Override
	public IngredientCommand saveOrUpdateIngredientCommand(IngredientCommand ingredientCommand) {
		
		Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());
		
		
		if(!recipeOptional.isPresent()) {
			
			log.error("not found id"+ingredientCommand.getId());
			return new IngredientCommand();
		}
		else {
			Recipe recipe = recipeOptional.get();
			
			Optional<Ingredient> ingredientOptional = recipe
					.getSetOfIngredient()
					.stream()
					.filter(ingredient->ingredient.getId().equals(ingredientCommand.getId()))
					.findFirst();
			
			if(ingredientOptional.isPresent()) {
				Ingredient ingredientFound = ingredientOptional.get();
				ingredientFound.setAmount(ingredientCommand.getAmount());
				ingredientFound.setDescription(ingredientCommand.getDescription());
				ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
						.findById(ingredientCommand.getUnitOfMeasureCommand().getId())
						.orElseThrow(()->new RuntimeException("UOM not Found")));
			}
			else {
				//add new Ingredient
				recipe.addIngredient(ingredientCommandToIngredient.convert(ingredientCommand));
			}
			
			Recipe savedRecipe = recipeRepository.save(recipe);
		
		return ingredientToIngredientCommand.convert(savedRecipe.getSetOfIngredient().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
				.findFirst()
				.get());
		}
	}

}
