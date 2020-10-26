package com.example.Recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Recipe.services.ImageService;
import com.example.Recipe.services.RecipeService;

@Controller
public class ImageController {

	private final RecipeService recipeService;
	
	private final ImageService imageService;

	public ImageController(RecipeService recipeService, ImageService imageService) {
		super();
		this.recipeService = recipeService;
		this.imageService = imageService;
	}
	
	@GetMapping("/recipe/image/{recipeId}")
	public String getImageForm(@PathVariable String recipeId, Model model) {
		model.addAttribute("recipe", recipeService.getRecipeCommandById(Long.valueOf(recipeId)));
		return "rrecipes/imageuploadform";
	}
	
	@PostMapping("/recipe/image/{recipeId}")
	public String getSaveImage(@PathVariable String recipeId, @RequestParam("imagefile") MultipartFile file) {
		imageService.saveImageFile(Long.valueOf(recipeId), file);
		return "redirect:/recipe/show/"+recipeId;
	}
}
