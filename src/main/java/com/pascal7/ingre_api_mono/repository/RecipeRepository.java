package com.pascal7.ingre_api_mono.repository;

import com.pascal7.ingre_api_mono.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String>, JpaSpecificationExecutor<Recipe> {
    List<Recipe> findByCategory(String category);
}
