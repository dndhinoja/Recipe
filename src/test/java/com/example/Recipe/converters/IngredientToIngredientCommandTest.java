package com.example.Recipe.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Recipe.Domain.Ingredient;
import com.example.Recipe.Domain.Recipe;
import com.example.Recipe.Domain.UnitOfMeasure;
import com.example.Recipe.commands.IngredientCommand;

class IngredientToIngredientCommandTest {
	
	IngredientToIngredientCommand converter;

	UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
	
	@BeforeEach
	void setUp() throws Exception {
		unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
		converter = new IngredientToIngredientCommand(unitOfMeasureToUnitOfMeasureCommand);
	}

	@Test
	void test() {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(1L);
		ingredient.setDescription("Hello");
		ingredient.setAmount(new BigDecimal("1"));
		
		Recipe recipe = new Recipe();
		ingredient.setRecipe(recipe);
		
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(2L);
		ingredient.setUnitOfMeasure(uom);
		
		IngredientCommand ingredientCommand = converter.convert(ingredient);
		
		//assertNull(ingredientCommand.getUnitOfMeasureCommand());
		assertEquals(ingredient.getDescription(), ingredientCommand.getDescription());
	}

}
