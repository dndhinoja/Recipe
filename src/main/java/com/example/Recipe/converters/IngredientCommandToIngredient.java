package com.example.Recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.Recipe.Domain.Ingredient;
import com.example.Recipe.commands.IngredientCommand;
import com.example.Recipe.commands.UnitOfMeasureCommand;
import com.example.Recipe.repositories.UnitOfMeasureRepository;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient>{

	private UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;
	
	public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {
		super();
		this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
	}

	@Override
	public Ingredient convert(IngredientCommand source) {
		if(source==null)
		return null;
		
		final Ingredient ingredient = new Ingredient();
		ingredient.setId(source.getId());
		ingredient.setDescription(source.getDescription());
		ingredient.setAmount(source.getAmount());
		ingredient.setUnitOfMeasure(unitOfMeasureCommandToUnitOfMeasure.convert(source.getUnitOfMeasureCommand()));
		return ingredient;
	}

}
