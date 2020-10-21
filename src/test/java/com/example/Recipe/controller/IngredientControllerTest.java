package com.example.Recipe.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.Recipe.commands.IngredientCommand;
import com.example.Recipe.commands.RecipeCommand;
import com.example.Recipe.services.IngredientService;
import com.example.Recipe.services.RecipeService;
import com.example.Recipe.services.UnitOfMeasureService;

class IngredientControllerTest {
	
	IngredientController ingredientController;
	@Mock
	RecipeService recipeService;
	
	@Mock
	IngredientService ingredientService; 
	
	@Mock
	UnitOfMeasureService unitOfMeasureService;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ingredientController = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
	}

	@Test
	void testGetIngredientById() throws Exception {
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(1L);
		
		when(recipeService.getRecipeCommandById(Mockito.anyLong())).thenReturn(recipeCommand);
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
		mockMvc.perform(get("/recipe/ingredients/1"))
		       .andExpect(status().isOk())
		       .andExpect(view().name("rrecipes/ingredient/list"));
		verify(recipeService, times(1)).getRecipeCommandById(Mockito.anyLong());
	}
	
	@Test
	void testIngredientCommandByIdAndRecipeIdController() throws Exception {
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(1L);
		ingredientCommand.setRecipeId(1L);
		
		when(ingredientService.getIngredientByIdAndRecipeId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(ingredientCommand);
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
		mockMvc.perform(get("/recipe/ingredient/1/show/1"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("rrecipes/ingredient/show"))
			   .andExpect(MockMvcResultMatchers.model().attributeExists("ingredient"));
		
	verify(ingredientService,times(1)).getIngredientByIdAndRecipeId(Mockito.anyLong(), Mockito.anyLong());
	}
	
	@Test
	void testUpdateRecipeIngredient() throws Exception {
		
		IngredientCommand ingredientCommand = new IngredientCommand();
		
		when(ingredientService.getIngredientByIdAndRecipeId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(ingredientCommand);
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
		mockMvc.perform(get("/recipe/ingredient/1/update/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("rrecipes/ingredient/ingredientform"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("ingredient"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("uomList"));
	}
	
	

}
