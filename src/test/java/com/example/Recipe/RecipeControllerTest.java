package com.example.Recipe;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
	public void testMockMvc() throws Exception{
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		mockMvc.perform(get("/recipes"))
			.andExpect(status().isOk())
			.andExpect(view().name("rrecipes/index"));
	}
	@Test
	public void testGetRecipeByIdController() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		when(recipeService.getRecipeById(Mockito.anyLong())).thenReturn(recipe);
		
		mockMvc.perform(get("/recipe/show/1")) 
			   .andExpect(status().isOk())
			   .andExpect(view().name("Display/view"))
			   .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
		 
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
