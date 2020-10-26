package com.example.Recipe.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.Recipe.commands.RecipeCommand;
import com.example.Recipe.services.ImageService;
import com.example.Recipe.services.RecipeService;

class ImageControllerTest {

	@Mock
	RecipeService recipeService;
	
	@Mock
	ImageService imageService;
	
	ImageController imageController;
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		imageController = new ImageController(recipeService, imageService);
	}

	@Test
	void testGetImageForm() throws Exception {
		
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(1L);
		when(recipeService.getRecipeCommandById(Mockito.anyLong())).thenReturn(recipeCommand);
		
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
		mockMvc.perform(get("/recipe/image/1"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("recipe"))
				.andExpect(view().name("rrecipes/imageuploadform"));
		
		verify(recipeService,times(1)).getRecipeCommandById(Mockito.anyLong());
	}
	
	@Test
	void testGetSaveImage() throws Exception{
		
		MockMultipartFile multiPartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                        "Spring Framework Guru".getBytes());
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
		mockMvc.perform(multipart("/recipe/image/1").file(multiPartFile))
			   .andExpect(status().is3xxRedirection())
			   .andExpect(header().string("Location", "/recipe/show/1"));
		
		verify(imageService, times(1)).saveImageFile(Long.valueOf(Mockito.anyLong()), Mockito.any());
	}
	
	

}
