package com.example.Recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Recipe.Domain.UnitOfMeasure;
import com.example.Recipe.repositories.CategoryRepository;
import com.example.Recipe.repositories.RecipeRepository;
import com.example.Recipe.repositories.UnitOfMeasureRepository;

@Controller
public class IndexController {

	//private CategoryRepository categoryRepository;
	//private UnitOfMeasureRepository unitOfMeasureRepository;
	
	private RecipeRepository recipeRepository;

	public IndexController(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}

	//public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		//super();
		//this.categoryRepository = categoryRepository;
		//this.unitOfMeasureRepository = unitOfMeasureRepository;
	//}

	@RequestMapping({ "", "/", "/index" })
	public String getResult(Model model) {
		model.addAttribute("recipes", recipeRepository.findAll());
		
		//System.out.println(categoryRepository.findByDescription("Mexican").get().getId());
		System.out.println("hi..");
		return "recipes/index";
	}

}
