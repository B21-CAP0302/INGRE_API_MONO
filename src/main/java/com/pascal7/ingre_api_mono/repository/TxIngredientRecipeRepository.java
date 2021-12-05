package com.pascal7.ingre_api_mono.repository;

import com.pascal7.ingre_api_mono.entity.Recipe;
import com.pascal7.ingre_api_mono.entity.TxIngredientRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TxIngredientRecipeRepository extends JpaRepository<TxIngredientRecipe, String>, JpaSpecificationExecutor<TxIngredientRecipe> {
    Optional<List<TxIngredientRecipe>> findByRecipe(Recipe recipe);
}
