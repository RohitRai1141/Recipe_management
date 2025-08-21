package com.recipe.dto;

import com.recipe.model.Recipe;
import java.util.HashMap;
import java.util.Map;

public class RecipeResponseDTO {
    private Long id;
    private String title;
    private String cuisine;
    private Double rating;
    private Integer prepTime;
    private Integer cookTime;
    private Integer totalTime;
    private String description;
    private String serves;
    private Map<String, String> nutrients;

    public RecipeResponseDTO() {}

    public RecipeResponseDTO(Recipe recipe) {
        this.id = recipe.getId();
        this.title = recipe.getTitle();
        this.cuisine = recipe.getCuisine();
        this.rating = recipe.getRating();
        this.prepTime = recipe.getPrepTime();
        this.cookTime = recipe.getCookTime();
        this.totalTime = recipe.getTotalTime();
        this.description = recipe.getDescription();
        this.serves = recipe.getServes();
        
        this.nutrients = new HashMap<>();
        this.nutrients.put("calories", recipe.getCalories());
        this.nutrients.put("carbohydrateContent", recipe.getCarbohydrateContent());
        this.nutrients.put("cholesterolContent", recipe.getCholesterolContent());
        this.nutrients.put("fiberContent", recipe.getFiberContent());
        this.nutrients.put("proteinContent", recipe.getProteinContent());
        this.nutrients.put("saturatedFatContent", recipe.getSaturatedFatContent());
        this.nutrients.put("sodiumContent", recipe.getSodiumContent());
        this.nutrients.put("sugarContent", recipe.getSugarContent());
        this.nutrients.put("fatContent", recipe.getFatContent());
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServes() {
        return serves;
    }

    public void setServes(String serves) {
        this.serves = serves;
    }

    public Map<String, String> getNutrients() {
        return nutrients;
    }

    public void setNutrients(Map<String, String> nutrients) {
        this.nutrients = nutrients;
    }
}
