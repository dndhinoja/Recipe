package com.example.Recipe.services;

import java.util.HashSet;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.Recipe.Domain.Recipe;
import com.example.Recipe.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipe() {
		log.debug("I m in Sevice");
		Set<Recipe> setOfRecipe = new HashSet<Recipe>();
		recipeRepository.findAll().iterator().forEachRemaining(setOfRecipe::add);
		return setOfRecipe;
	}

}
