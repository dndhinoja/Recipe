package com.example.Recipe;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.example.Recipe.Domain.Recipe;
import com.example.Recipe.controller.RecipeController;
import com.example.Recipe.services.RecipeService;

public class RecipeControllerTest {

	RecipeController recipeController;
	
	@Mock
	RecipeService recipeService;
	
	@Mock
	Model model;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeController = new RecipeController(recipeService);
	}

	@Test
	public void testGetResult() {
		
		Set<Recipe> recipes = new HashSet<>();
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		recipes.add(recipe);
		Recipe recipe1 = new Recipe();
		recipe1.setId(2L);
		recipes.add(recipe1);
		
		when(recipeService.getRecipe()).thenReturn(recipes);
		
		ArgumentCaptor<Set<Recipe>> captor = ArgumentCaptor.forClass(Set.class);
		
		String template = recipeController.getResult(model);
		
		assertEquals("Checking for returning string","rrecipes/index", template);
		
		verify(recipeService, times(1)).getRecipe();
		
		verify(model, times(1)).addAttribute(eq("recipes"), captor.capture());
		
		Set<Recipe> captorRecipes = captor.getValue();
		assertEquals(2, captorRecipes.size());
	}

}
