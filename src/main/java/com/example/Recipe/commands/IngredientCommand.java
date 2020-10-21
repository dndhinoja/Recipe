package com.example.Recipe.commands;

import java.math.BigDecimal;

import com.example.Recipe.Domain.UnitOfMeasure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {

	private Long id;
	private String description;
	private BigDecimal amount;
	private UnitOfMeasureCommand unitOfMeasureCommand;
	
	private Long recipeId;
}
