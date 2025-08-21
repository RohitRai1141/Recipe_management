package com.recipe.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.model.Recipe;
import com.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class RecipeDataLoaderService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public void loadRecipesFromJson(String jsonFilePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(jsonFilePath);
        
        // Read JSON as a Map where keys are indices and values are recipe objects
        Map<String, JsonNode> recipesMap = objectMapper.readValue(
            resource.getInputStream(), 
            new TypeReference<Map<String, JsonNode>>() {}
        );

        for (Map.Entry<String, JsonNode> entry : recipesMap.entrySet()) {
            JsonNode recipeNode = entry.getValue();
            
            // Skip entries that don't have required fields
            if (!recipeNode.has("title") || !recipeNode.has("cuisine")) {
                continue;
            }

            Recipe recipe = new Recipe();
            
            // Map basic fields
            recipe.setTitle(getStringValue(recipeNode, "title"));
            recipe.setCuisine(getStringValue(recipeNode, "cuisine"));
            recipe.setDescription(getStringValue(recipeNode, "description"));
            recipe.setServes(getStringValue(recipeNode, "serves"));
            
            // Map numeric fields
            recipe.setRating(getDoubleValue(recipeNode, "rating"));
            recipe.setPrepTime(getIntegerValue(recipeNode, "prep_time"));
            recipe.setCookTime(getIntegerValue(recipeNode, "cook_time"));
            recipe.setTotalTime(getIntegerValue(recipeNode, "total_time"));
            
            // Map nutrient fields
            JsonNode nutrients = recipeNode.get("nutrients");
            if (nutrients != null) {
                recipe.setCalories(getStringValue(nutrients, "calories"));
                recipe.setCarbohydrateContent(getStringValue(nutrients, "carbohydrateContent"));
                recipe.setCholesterolContent(getStringValue(nutrients, "cholesterolContent"));
                recipe.setFiberContent(getStringValue(nutrients, "fiberContent"));
                recipe.setProteinContent(getStringValue(nutrients, "proteinContent"));
                recipe.setSaturatedFatContent(getStringValue(nutrients, "saturatedFatContent"));
                recipe.setSodiumContent(getStringValue(nutrients, "sodiumContent"));
                recipe.setSugarContent(getStringValue(nutrients, "sugarContent"));
                recipe.setFatContent(getStringValue(nutrients, "fatContent"));
            }
            
            // Set timestamps
            recipe.setCreatedAt(LocalDateTime.now());
            recipe.setUpdatedAt(LocalDateTime.now());
            
            // Save to database
            recipeRepository.save(recipe);
        }
    }

    private String getStringValue(JsonNode node, String fieldName) {
        JsonNode field = node.get(fieldName);
        return field != null && !field.isNull() ? field.asText() : null;
    }

    private Double getDoubleValue(JsonNode node, String fieldName) {
        JsonNode field = node.get(fieldName);
        return field != null && !field.isNull() ? field.asDouble() : null;
    }

    private Integer getIntegerValue(JsonNode node, String fieldName) {
        JsonNode field = node.get(fieldName);
        return field != null && !field.isNull() ? field.asInt() : null;
    }
}
