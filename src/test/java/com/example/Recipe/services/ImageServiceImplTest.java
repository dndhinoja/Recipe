package com.example.Recipe.services;

import static org.mockito.Mockito.when;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.Recipe.repositories.RecipeRepository;

class ImageServiceImplTest {
	
	@Mock
	RecipeRepository recipeRepository;
	
	ImageService imageService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(imageService);
		imageService = new ImageServiceImpl();
	}
	@Ignore
	@Test
	void test() {
		
		
		//when(recipeRepository.findById(Mockito.anyLong())).thenReturn(value);
	}

}
