package com.example.Recipe;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.example.Recipe.Domain.Recipe;
import com.example.Recipe.commands.RecipeCommand;
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
	public void testMockMvc() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		mockMvc.perform(get("/recipes")).andExpect(status().isOk()).andExpect(view().name("rrecipes/index"));
	}

	@Test
	public void testGetRecipeByIdController() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		when(recipeService.getRecipeById(Mockito.anyLong())).thenReturn(recipe);

		mockMvc.perform(get("/recipe/show/1")).andExpect(status().isOk()).andExpect(view().name("Display/view"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));

	}
	@Test
	public void testNewRecipe() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

		mockMvc.perform(get("/recipe/new"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("rrecipes/addrecipe"))
			   .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
	}
	@Test
	public void testsaveOrUpdate() throws Exception {
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(2L);

		when(recipeService.saveRecipeCommand(Mockito.any())).thenReturn(recipeCommand);

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		mockMvc.perform(post("/recipe").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", "").param("cookingTime", "some String"))
			    
			   .andExpect(status().is3xxRedirection())
			   .andExpect(view().name("redirect:/recipe/show/2"));
	}
	
	@Test
	public void testUpdate() throws Exception{
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(2L);
		
		when(recipeService.getRecipeCommandById(Mockito.anyLong())).thenReturn(recipeCommand);
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		mockMvc.perform(get("/recipe/update/2"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("/rrecipe/addrecipe"))
			   .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
	}
	
	@Test
	public void testDelete() throws Exception{
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		mockMvc.perform(get("/recipe/delete/1"))
		       .andExpect(status().is3xxRedirection())
		       .andExpect(view().name("redirect:/recipes"));
		
		verify(recipeService,times(1)).deleteRecipeById(Mockito.anyLong());
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

		assertEquals("Checking for returning string", "rrecipes/index", template);

		verify(recipeService, times(1)).getRecipe();

		verify(model, times(1)).addAttribute(eq("recipes"), captor.capture());

		Set<Recipe> captorRecipes = captor.getValue();
		assertEquals(2, captorRecipes.size());
	}

}
