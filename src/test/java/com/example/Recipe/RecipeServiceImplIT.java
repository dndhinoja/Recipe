package com.example.Recipe;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Recipe.Domain.Recipe;
import com.example.Recipe.commands.RecipeCommand;
import com.example.Recipe.converters.RecipeCommandToRecipe;
import com.example.Recipe.converters.RecipeToRecipeCommand;
import com.example.Recipe.repositories.RecipeRepository;
import com.example.Recipe.services.RecipeService;

@RunWith(SpringRunner.class)
@SpringBootTest
class RecipeServiceImplIT {

	@Autowired
	RecipeService recipeService;
	@Autowired
	RecipeRepository recipeRepository;
	@Autowired
	RecipeToRecipeCommand recipeToRecipeCommand;
	@Autowired
	RecipeCommandToRecipe recipeCommandToRecipe;
	
	@Transactional
	@Test
	public void test() {
		
		Iterable<Recipe> recipe = recipeRepository.findAll();
		Recipe testRecipe = recipe.iterator().next();
		RecipeCommand recipeCommand = recipeToRecipeCommand.convert(testRecipe);
		
		recipeCommand.setDescription("DIPALI");
		RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(recipeCommand);
		
		assertEquals("DIPALI", savedRecipeCommand.getDescription());
	}

}
