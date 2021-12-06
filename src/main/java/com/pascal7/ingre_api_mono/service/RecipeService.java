package com.pascal7.ingre_api_mono.service;

import com.pascal7.ingre_api_mono.custom.Category;
import com.pascal7.ingre_api_mono.custom.RecipeDto;
import com.pascal7.ingre_api_mono.entity.Recipe;

import java.util.List;

public interface RecipeService extends CRUDServiceTemplate<RecipeDto>{
    List<Category> categories();
    List<Recipe> recipeByCategory(String category);
}
