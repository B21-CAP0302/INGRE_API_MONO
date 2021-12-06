package com.pascal7.ingre_api_mono.custom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pascal7.ingre_api_mono.entity.Recipe;
import com.pascal7.ingre_api_mono.entity.TxIngredientRecipe;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RecipeDto {
    private String id;

    @NotBlank
    private String name;
    @NotBlank
    private String recipeDetail;
    @JsonIgnore
    private Timestamp date;
    @NotBlank
    private List<IngredientDto> ingredients = new ArrayList<>();
    private String category;
    private String photo;

    public RecipeDto() {
    }

    public RecipeDto(Recipe recipe, String recipeDetail, List<TxIngredientRecipe> ingredients){
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.date = recipe.getDate();
        this.photo = recipe.getPhoto();
        this.recipeDetail = recipeDetail;
        this.category = recipe.getCategory();
        ingredients.forEach(
                txIngredientRecipe -> {
                    this.ingredients.add(new IngredientDto(txIngredientRecipe));
                }
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipeDetail() {
        return recipeDetail;
    }

    public void setRecipeDetail(String recipeDetail) {
        this.recipeDetail = recipeDetail;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
