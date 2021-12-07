package com.pascal7.ingre_api_mono.custom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pascal7.ingre_api_mono.entity.Ingredient;
import com.pascal7.ingre_api_mono.entity.TxIngredientRecipe;

public class IngredientDto {

    private String ingredientId;
    private Ingredient ingredient;
    private Integer qty;

    public IngredientDto(Ingredient ingredient, Integer qty) {
        this.ingredientId = ingredient.getId();
        this.ingredient = ingredient;
        this.qty = qty;
    }

    public IngredientDto(TxIngredientRecipe txIngredientRecipe){
        this.ingredientId = txIngredientRecipe.getIngredient().getId();
        this.ingredient = txIngredientRecipe.getIngredient();
        this.qty = txIngredientRecipe.getQuantity();
    }

    public IngredientDto() {
    }

    public String getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(String ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
