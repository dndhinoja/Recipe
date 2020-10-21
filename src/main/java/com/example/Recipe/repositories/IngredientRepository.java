package com.example.Recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.Recipe.Domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long>{

}
