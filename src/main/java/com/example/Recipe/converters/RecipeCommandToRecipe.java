package com.example.Recipe.converters;

import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.Recipe.Domain.Category;
import com.example.Recipe.Domain.Ingredient;
import com.example.Recipe.Domain.Recipe;
import com.example.Recipe.commands.CategoryCommand;
import com.example.Recipe.commands.IngredientCommand;
import com.example.Recipe.commands.RecipeCommand;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

	private final NotesCommandToNotes notesCommandToNotes;
	private final CategoryCommandToCategory categoryCommandToCategory;
	private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;

	public RecipeCommandToRecipe(NotesCommandToNotes notesCommandToNotes,
			CategoryCommandToCategory categoryCommandToCategory,
			UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure,
			IngredientCommandToIngredient ingredientCommandToIngredient) {
		super();
		this.notesCommandToNotes = notesCommandToNotes;
		this.categoryCommandToCategory = categoryCommandToCategory;
		this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
	}

	@Override
	public Recipe convert(RecipeCommand source) {
		if (source == null)
			return null;
		final Recipe recipe = new Recipe();
		recipe.setId(source.getId());
		recipe.setDescription(source.getDescription());
		recipe.setCookTime(source.getCookTime());
		recipe.setDifficulty(source.getDifficulty());
		recipe.setDirections(source.getDirections());
		recipe.setNotes(notesCommandToNotes.convert(source.getNotes()));
		recipe.setPrepTime(source.getPrepTime());
		recipe.setServings(source.getServings());
		recipe.setUrl(source.getUrl());
		recipe.setSource(source.getSource());

		Set<Category> categories = new HashSet<Category>();
		Category category1 = new Category();
		if (source.getSetOfCategoryCommand() != null) {
			for (CategoryCommand category : source.getSetOfCategoryCommand()) {
				category1 = categoryCommandToCategory.convert(category);
				categories.add(category1);
			}
			recipe.setSetOfCategory(categories);
		}

		Set<Ingredient> ingredients = new HashSet<Ingredient>();
		Ingredient ingredient = new Ingredient();
		if (source.getSetOfIngredientCommand() != null) {
			for (IngredientCommand ingredientCommand : source.getSetOfIngredientCommand()) {
				ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
				ingredients.add(ingredient);
			}
			recipe.setSetOfIngredient(ingredients);
		}
		return recipe;
	}
}
