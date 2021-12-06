package com.pascal7.ingre_api_mono.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pascal7.ingre_api_mono.custom.RecipeDto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "mst_recipe")
public class Recipe {

    @Id
    @GeneratedValue(generator = "recipe_uuid")
    @GenericGenerator(name = "recipe_uuid", strategy = "uuid")
    private String id;

    private String name;
    @JsonIgnore
    private Timestamp date;
    private String category;
    private String photo;

    public Recipe() {
    }

    public Recipe(RecipeDto recipeDto){
        this.id = recipeDto.getId();
        this.name = recipeDto.getName();
        this.date = recipeDto.getDate();
        this.category = recipeDto.getCategory();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getPhoto() {
        return photo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
