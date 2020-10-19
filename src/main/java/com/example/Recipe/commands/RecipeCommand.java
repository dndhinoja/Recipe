package com.example.Recipe.commands;

import java.util.HashSet;
import java.util.Set;

import com.example.Recipe.Domain.Difficulty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;
	//private Byte[] image;
	private NotesCommand notes;
	private Set<IngredientCommand> setOfIngredientCommand = new HashSet<IngredientCommand>();
	private Difficulty  difficulty;
	private Set<CategoryCommand> setOfCategoryCommand = new HashSet<CategoryCommand>();
}
