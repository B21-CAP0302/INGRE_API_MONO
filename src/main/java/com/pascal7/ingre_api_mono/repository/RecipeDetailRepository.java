package com.pascal7.ingre_api_mono.repository;

import com.pascal7.ingre_api_mono.entity.Recipe;
import com.pascal7.ingre_api_mono.entity.RecipeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeDetailRepository extends JpaRepository<RecipeDetail, String>, JpaSpecificationExecutor<RecipeDetail> {
    Optional<List<RecipeDetail>> findByRecipe(Recipe recipe);
}
