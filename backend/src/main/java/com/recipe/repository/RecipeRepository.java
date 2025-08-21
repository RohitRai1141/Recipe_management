package com.recipe.repository;

import com.recipe.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    
    @Query("SELECT r FROM Recipe r WHERE " +
           "(:title IS NULL OR LOWER(r.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
           "(:cuisine IS NULL OR r.cuisine = :cuisine) AND " +
           "(:minRating IS NULL OR r.rating >= :minRating) AND " +
           "(:maxTime IS NULL OR r.totalTime <= :maxTime) AND " +
           "(:maxCalories IS NULL OR CAST(SUBSTRING(r.calories, 1, LOCATE(' ', r.calories) - 1) AS INTEGER) <= :maxCalories)")
    Page<Recipe> findRecipesWithFilters(
        @Param("title") String title,
        @Param("cuisine") String cuisine,
        @Param("minRating") Double minRating,
        @Param("maxTime") Integer maxTime,
        @Param("maxCalories") Integer maxCalories,
        Pageable pageable
    );
}
