package com.example.Recipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.Recipe.controller.IndexController;

@SpringBootApplication
public class RecipeApplication {

	public static void main(String[] args) {
		/* ApplicationContext ctx = */ SpringApplication.run(RecipeApplication.class, args);
		
		/*
		 * IndexController indexController =
		 * (IndexController)ctx.getBean(IndexController.class);
		 * System.out.println(indexController.getResult());
		 */
	}

}
