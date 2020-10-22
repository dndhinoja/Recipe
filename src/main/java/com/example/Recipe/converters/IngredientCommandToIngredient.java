package com.example.Recipe.converters;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.Recipe.Domain.Ingredient;
import com.example.Recipe.Domain.Recipe;
import com.example.Recipe.commands.IngredientCommand;
import com.example.Recipe.repositories.RecipeRepository;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

	private UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

	public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {
		super();
		this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
	}

	@Nullable
	@Override
	public Ingredient convert(IngredientCommand source) {
		if (source == null) {
			return null;
		}

		final Ingredient ingredient = new Ingredient();
		ingredient.setId(source.getId());

		if (source.getRecipeId() != null) {

			if (source.getRecipeId() != null) {
				Recipe recipe = new Recipe();
				recipe.setId(source.getRecipeId());
				ingredient.setRecipe(recipe);
				recipe.addIngredient(ingredient);
			}

			/*
			 * Iterable<Recipe> it = recipeRepository.findAll(); Set<Recipe> listOfRecipe =
			 * StreamSupport.stream(it.spliterator(), false) .collect(Collectors.toSet());
			 * 
			 * Optional<Recipe> recipe1 = listOfRecipe.stream(). filter(recipe ->
			 * recipe.getId().equals(source.getRecipeId())).findFirst(); Recipe recipe =
			 * recipe1.get();
			 * 
			 * //ingredient.setRecipe(recipe); recipe.addIngredient(ingredient);
			 */
		}

		ingredient.setDescription(source.getDescription());
		ingredient.setAmount(source.getAmount());
		ingredient.setUnitOfMeasure(unitOfMeasureCommandToUnitOfMeasure.convert(source.getUnitOfMeasureCommand()));

		return ingredient;
	}

}
