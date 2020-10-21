package com.example.Recipe.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.Recipe.Domain.Ingredient;
import com.example.Recipe.Domain.Recipe;
import com.example.Recipe.commands.IngredientCommand;
import com.example.Recipe.converters.IngredientCommandToIngredient;
import com.example.Recipe.converters.IngredientToIngredientCommand;
import com.example.Recipe.repositories.IngredientRepository;
import com.example.Recipe.repositories.RecipeRepository;
import com.example.Recipe.repositories.UnitOfMeasureRepository;

class IngredientServiceImplTest {

	IngredientService ingredientService;

	@Mock
	RecipeRepository recipeRepository;
	@Mock
	IngredientToIngredientCommand ingredientToIngredientCommand;
	@Mock
	IngredientRepository ingredientRepository;
	@Mock
	IngredientCommandToIngredient ingredientCommandToIngredient;
	@Mock
	UnitOfMeasureRepository unitOfMeasureRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ingredientService = new IngredientServiceImpl(recipeRepository,ingredientRepository,ingredientToIngredientCommand,ingredientCommandToIngredient,unitOfMeasureRepository);
	}

	@Test
	void testGetIngredientByIdAndRecipeId() throws Exception{
		
		Recipe recipe = new Recipe();
		recipe.setId(1L);

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1L);
		
		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId(2L);
		
		recipe.addIngredient(ingredient1);
		recipe.addIngredient(ingredient2);
		
		Optional<Recipe> recipeOptional = Optional.of(recipe);

		when(recipeRepository.findById(Mockito.anyLong())).thenReturn(recipeOptional);
		
		//IngredientCommand ingredientCommand = ingredientService.getIngredientByIdAndRecipeId(1L, 1L);
		//System.out.println(ingredientCommand.getId());
		
		//assertEquals(Long.valueOf(2L), ingredientCommand.getId());
		//assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
	}
	
	@Test
	void testSavedOrUpdateIngredientCommand() {
		
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setRecipeId(1L);
		ingredientCommand.setId(1L);

		Recipe savedRecipe = new Recipe();
		savedRecipe.addIngredient(new Ingredient());
		savedRecipe.getSetOfIngredient().iterator().next().setId(1L);
		
		Optional<Recipe> recipeOptional = Optional.of(new Recipe());
		
		when(recipeRepository.findById(Mockito.anyLong())).thenReturn(recipeOptional);
		when(recipeRepository.save(Mockito.any())).thenReturn(savedRecipe);
		
		
		IngredientCommand savedIngredientCommand = ingredientService.saveOrUpdateIngredientCommand(ingredientCommand);
		
		assertEquals(Long.valueOf(1L), savedIngredientCommand.getRecipeId());
		
	}

}
