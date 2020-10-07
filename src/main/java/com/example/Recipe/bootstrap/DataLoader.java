package com.example.Recipe.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.Recipe.Domain.Category;
import com.example.Recipe.Domain.Difficulty;
import com.example.Recipe.Domain.Ingredient;
import com.example.Recipe.Domain.Notes;
import com.example.Recipe.Domain.Recipe;
import com.example.Recipe.Domain.UnitOfMeasure;
import com.example.Recipe.repositories.CategoryRepository;
import com.example.Recipe.repositories.RecipeRepository;
import com.example.Recipe.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent>{

	private RecipeRepository recipeRepository;
	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;
	
	public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.recipeRepository = recipeRepository;
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	private List<Recipe> dataLoader() {
		//from data.sql
		Optional<Category> american = categoryRepository.findByDescription("American");
		if(!american.isPresent()) {
			throw new RuntimeException("given category type is not present");
		}
		Optional<Category> mexican = categoryRepository.findByDescription("Mexican");
		if(!mexican.isPresent()) {
			throw new RuntimeException("given category type is not present");
		}
		
		Category americanCategory = american.get();
		Category mexicanCategory =mexican.get();
		
		Optional<UnitOfMeasure> teaSpoon = unitOfMeasureRepository.findByUom("Teaspoon");
		if(!teaSpoon.isPresent()) {
			throw new RuntimeException("TeaSpoon not found");
		}
		UnitOfMeasure teaSpoonUom = teaSpoon.get();
		
		Optional<UnitOfMeasure> tableSpoon = unitOfMeasureRepository.findByUom("Tablespoon");
		if(!tableSpoon.isPresent()) {
			throw new RuntimeException("TableSpoon not found");
		}
		UnitOfMeasure tableSpoonUom = tableSpoon.get();
		//Ending data.sql
		
		List<Recipe> listOfRecipes = new ArrayList<>();
				
		Recipe perfectGuacamole = new Recipe();
		perfectGuacamole.setPrepTime(10);
		perfectGuacamole.setCookTime(0);
		perfectGuacamole.setDirections("1.Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon."
				+ "	2. Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)");
		perfectGuacamole.setServings(3);
		perfectGuacamole.setDescription("Be careful handling chiles");
		perfectGuacamole.setDifficulty(Difficulty.EASY);
		
		
		/*
		 * Set<Category> setOfCategory = new HashSet<Category>();
		 * setOfCategory.add(americanCategory); setOfCategory.add(mexicanCategory);
		 * perfectGuacamole.setSetOfCategory(setOfCategory);
		 */
		 	
		perfectGuacamole.getSetOfCategory().add(americanCategory);
		perfectGuacamole.getSetOfCategory().add(mexicanCategory);
		
		Set<Ingredient> setOfIngredient = new HashSet<Ingredient>();
		//Ingredient avocados1 = new Ingredient("avocado is must",new BigDecimal(2),perfectGuacamole,teaSpoonUom);
		//Ingredient onion2 = new Ingredient("onion is must",new BigDecimal(1),perfectGuacamole,tableSpoonUom);
		//perfectGuacamole.getSetOfIngredient().add(avocados1);
		//perfectGuacamole.getSetOfIngredient().add(onion2);
		Ingredient avocados11 = new Ingredient("avocado is must",new BigDecimal(2),teaSpoonUom);
		Ingredient onion2 = new Ingredient("onion is must",new BigDecimal(1),tableSpoonUom);
		perfectGuacamole.addIngredient(avocados11);
		perfectGuacamole.addIngredient(onion2);
		
		Notes note1 = new Notes();
		note1.setRecipeNotes("Hii..I have tried it..It's delicious");
		perfectGuacamole.setNotes(note1);
		//note1.setRecipe(perfectGuacamole);
		log.info("Hi I am done");
		listOfRecipes.add(perfectGuacamole);
		return listOfRecipes;
	}
	
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		recipeRepository.saveAll(dataLoader());
	}

}
