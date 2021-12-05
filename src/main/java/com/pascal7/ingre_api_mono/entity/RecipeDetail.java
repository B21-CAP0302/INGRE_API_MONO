package com.pascal7.ingre_api_mono.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pascal7.ingre_api_mono.custom.RecipeDto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tx_recipe_detail")
public class RecipeDetail {

    @Id
    @GeneratedValue(generator = "tx_recipe_detail_uuid")
    @GenericGenerator(name = "tx_recipe_detail_uuid", strategy = "uuid")
    private String id;

    @Transient
    private String recipeId;

    @ManyToOne
    @JoinColumn(name = "recipeId")
    private Recipe recipe;

    private String detail;

    public RecipeDetail() {
    }

    public RecipeDetail(Recipe recipe, String detail){
        this.recipe = recipe;
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public String getRecipeId() {
        return recipeId;
    }

    @JsonIgnore
    public Recipe getRecipe() {
        return recipe;
    }

    public String getDetail() {
        return detail;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
