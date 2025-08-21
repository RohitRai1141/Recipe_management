package com.recipe.controller;

import com.recipe.dto.PagedResponseDTO;
import com.recipe.dto.RecipeResponseDTO;
import com.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000", "http://127.0.0.1:5173"})
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public ResponseEntity<PagedResponseDTO<RecipeResponseDTO>> getAllRecipes(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int limit) {
        
        PagedResponseDTO<RecipeResponseDTO> response = recipeService.getAllRecipes(page, limit);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<PagedResponseDTO<RecipeResponseDTO>> searchRecipes(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String cuisine,
            @RequestParam(required = false) Double rating,
            @RequestParam(name = "total_time", required = false) Integer totalTime,
            @RequestParam(required = false) Integer calories,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "15") int limit) {
        
        PagedResponseDTO<RecipeResponseDTO> response = recipeService.searchRecipes(
                title, cuisine, rating, totalTime, calories, page, limit);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponseDTO> getRecipeById(@PathVariable Long id) {
        Optional<RecipeResponseDTO> recipe = recipeService.getRecipeById(id);
        return recipe.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
}
