package com.recipe.controller;

import com.recipe.service.RecipeDataLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/data")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000", "http://127.0.0.1:5173"})
public class DataLoaderController {

    @Autowired
    private RecipeDataLoaderService recipeDataLoaderService;

    @PostMapping("/load-recipes")
    public ResponseEntity<String> loadRecipes(@RequestParam(defaultValue = "recipes.json") String fileName) {
        try {
            recipeDataLoaderService.loadRecipesFromJson(fileName);
            return ResponseEntity.ok("Recipes loaded successfully from " + fileName);
        } catch (IOException e) {
            return ResponseEntity.badRequest()
                .body("Error loading recipes: " + e.getMessage());
        }
    }
}
