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
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand>{

	private final NotesToNotesCommand notesToNotesCommand;
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final CategoryToCategoryCommand categoryToCategoryCommand;
	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
	
	public RecipeToRecipeCommand(NotesToNotesCommand notesToNotesCommand,
			IngredientToIngredientCommand ingredientToIngredientCommand,
			CategoryToCategoryCommand categoryToCategoryCommand,
			UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
		super();
		this.notesToNotesCommand = notesToNotesCommand;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.categoryToCategoryCommand = categoryToCategoryCommand;
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}

	@Override
	public RecipeCommand convert(Recipe source) {
		if(source==null)
		return null;
		
		final RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(source.getId());
		recipeCommand.setCookTime(source.getCookTime());
		recipeCommand.setDescription(source.getDescription());
		recipeCommand.setDifficulty(source.getDifficulty());
		recipeCommand.setDirections(source.getDirections());
		recipeCommand.setPrepTime(source.getPrepTime());
		recipeCommand.setServings(source.getServings());
		recipeCommand.setSource(source.getSource());
		recipeCommand.setUrl(source.getUrl());
		recipeCommand.setNotes(notesToNotesCommand.convert(source.getNotes()));
		
		CategoryCommand categoryCommand = new CategoryCommand();
		Set<CategoryCommand> categoryCommands = new HashSet<CategoryCommand>();
		for(Category category:source.getSetOfCategory()) {
			categoryCommand = categoryToCategoryCommand.convert(category);
			categoryCommands.add(categoryCommand);
		}
		recipeCommand.setSetOfCategoryCommand(categoryCommands);
		
		IngredientCommand ingredientCommand = new IngredientCommand();
		Set<IngredientCommand> ingredientCommands = new HashSet<IngredientCommand>();
		for(Ingredient ingredient:source.getSetOfIngredient()) {
			ingredientCommand = ingredientToIngredientCommand.convert(ingredient);
			ingredientCommands.add(ingredientCommand);
		}
		recipeCommand.setSetOfIngredientCommand(ingredientCommands);
		
		return recipeCommand;
	}
	
}
