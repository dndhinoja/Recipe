package com.example.Recipe;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.Recipe.Domain.Recipe;
import com.example.Recipe.converters.RecipeCommandToRecipe;
import com.example.Recipe.converters.RecipeToRecipeCommand;
import com.example.Recipe.repositories.RecipeRepository;
import com.example.Recipe.services.RecipeServiceImpl;

public class RecipeServiceImplTest {

	RecipeServiceImpl recipeService;

	@Mock
	RecipeRepository recipeRepository;
	@Mock
	RecipeToRecipeCommand recipeToRecipeCommand;

	@Mock
	RecipeCommandToRecipe recipeCommandToRecipe;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository,recipeCommandToRecipe,recipeToRecipeCommand);
	}

	@Test
	public void testGetRecipeById() {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		recipe.setDescription("Dipali...hahaha");
		Optional<Recipe> recipeOptional = Optional.of(recipe);

		when(recipeRepository.findById(Mockito.anyLong())).thenReturn(recipeOptional);
		Recipe reci = recipeService.getRecipeById(1L);
		assertEquals("Dipali...hahaha", reci.getDescription());
		verify(recipeRepository, times(1)).findById(Mockito.anyLong());
	}

	@Test
	public void testGetRecipe() {
		Recipe recipe = new Recipe();
		HashSet<Recipe> setOfRecipe = new HashSet<>();
		setOfRecipe.add(recipe);

		when(recipeRepository.findAll()).thenReturn(setOfRecipe);
		Set<Recipe> recipes = recipeService.getRecipe();
		assertEquals(recipes.size(), 1);
		verify(recipeRepository, times(1)).findAll();
	}
	@Test
	public void testDeleteRecipeById() {
		recipeService.deleteRecipeById(1L);
		
		verify(recipeRepository,times(1)).deleteById(Mockito.anyLong());
	}	

}
