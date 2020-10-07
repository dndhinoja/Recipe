package com.example.Recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.Recipe.Domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long>{

}
