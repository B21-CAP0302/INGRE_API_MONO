package com.pascal7.ingre_api_mono.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tx_ingredient_recipe")
public class TxIngredientRecipe {

    @Id
    @GeneratedValue(generator = "ingredient_recipe_uuid")
    @GenericGenerator(name = "ingredient_recipe_uuid", strategy = "uuid")
    private String id;

    @Transient
    private String recipeId;

    @ManyToOne
    @JoinColumn(name = "recipeId")
    private Recipe recipe;

    @Transient
    private String ingredientId;

    @ManyToOne
    @JoinColumn(name = "ingredientId")
    private Ingredient ingredient;

    private Integer quantity;

    public TxIngredientRecipe() {
    }

    public TxIngredientRecipe(Recipe recipe, Ingredient ingredient, Integer quantity) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
