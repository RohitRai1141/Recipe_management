package com.recipe.service;

import com.recipe.dto.PagedResponseDTO;
import com.recipe.dto.RecipeResponseDTO;
import com.recipe.model.Recipe;
import com.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public PagedResponseDTO<RecipeResponseDTO> getAllRecipes(int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("id").ascending());
        Page<Recipe> recipePage = recipeRepository.findAll(pageable);
        
        List<RecipeResponseDTO> recipes = recipePage.getContent().stream()
                .map(RecipeResponseDTO::new)
                .collect(Collectors.toList());
        
        return new PagedResponseDTO<>(recipes, page, limit, recipePage.getTotalElements());
    }

    public PagedResponseDTO<RecipeResponseDTO> searchRecipes(
            String title, String cuisine, Double rating, Integer totalTime, 
            Integer calories, int page, int limit) {
        
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by("id").ascending());
        
        // Convert empty strings to null for proper query handling
        String titleParam = (title != null && title.trim().isEmpty()) ? null : title;
        String cuisineParam = (cuisine != null && cuisine.trim().isEmpty()) ? null : cuisine;
        
        Page<Recipe> recipePage = recipeRepository.findRecipesWithFilters(
                titleParam, cuisineParam, rating, totalTime, calories, pageable);
        
        List<RecipeResponseDTO> recipes = recipePage.getContent().stream()
                .map(RecipeResponseDTO::new)
                .collect(Collectors.toList());
        
        return new PagedResponseDTO<>(recipes, page, limit, recipePage.getTotalElements());
    }

    public Optional<RecipeResponseDTO> getRecipeById(Long id) {
        return recipeRepository.findById(id)
                .map(RecipeResponseDTO::new);
    }
}
