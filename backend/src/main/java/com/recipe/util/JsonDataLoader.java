package com.recipe.util;

import com.recipe.service.RecipeDataLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Utility class to automatically load JSON data on application startup
 * Uncomment the @Component annotation to enable automatic loading
 */
// @Component
public class JsonDataLoader implements CommandLineRunner {

    @Autowired
    private RecipeDataLoaderService recipeDataLoaderService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading recipes from JSON file...");
        try {
            recipeDataLoaderService.loadRecipesFromJson("recipes.json");
            System.out.println("Recipes loaded successfully!");
        } catch (Exception e) {
            System.err.println("Error loading recipes: " + e.getMessage());
        }
    }
}
