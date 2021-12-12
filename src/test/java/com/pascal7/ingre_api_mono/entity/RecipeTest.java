package com.pascal7.ingre_api_mono.entity;

import com.pascal7.ingre_api_mono.custom.RecipeDto;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    @Test
    void constructor1(){
        Recipe recipe = new Recipe();
        assertNotNull(recipe);
    }

    @Test
    void constructor2(){
        Recipe recipe = new Recipe(new RecipeDto());
        assertNotNull(recipe);
    }

    @Test
    void getId() {
        Recipe recipe = new Recipe();
        ReflectionTestUtils.setField(recipe, "id", "123");
        assertNotNull(recipe.getId());
    }

    @Test
    void setId() {
        Recipe recipe = new Recipe();
        String id =  "321";
        ReflectionTestUtils.setField(recipe, "id", id);
        assertEquals(id, recipe.getId());
    }

    @Test
    void getName() {
        Recipe recipe = new Recipe();
        String name = "resep";
        recipe.setName(name);
        assertNotNull(recipe.getName());
    }

    @Test
    void setName() {
        Recipe recipe = new Recipe();
        String name = "resep";
        recipe.setName(name);
        assertEquals(name, recipe.getName());
    }

    @Test
    void getDate() {
        Recipe recipe = new Recipe();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        recipe.setDate(timestamp);
        assertNotNull(recipe.getDate());
    }

    @Test
    void setDate() {
        Recipe recipe = new Recipe();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        recipe.setDate(timestamp);
        assertEquals(timestamp, recipe.getDate());
    }

    @Test
    void getPhoto() {
        Recipe recipe = new Recipe();
        String photo = "https://photo.com";
        recipe.setPhoto(photo);
        assertNotNull(recipe.getPhoto());
    }

    @Test
    void setPhoto() {
        Recipe recipe = new Recipe();
        String photo = "https://photo.com";
        recipe.setPhoto(photo);
        assertEquals(photo, recipe.getPhoto());
    }

    @Test
    void getCategory() {
        Recipe recipe = new Recipe();
        String category = "category";
        recipe.setCategory(category);
        assertNotNull(recipe.getCategory());
    }

    @Test
    void setCategory() {
        Recipe recipe = new Recipe();
        String category = "category";
        recipe.setCategory(category);
        assertEquals(category, recipe.getCategory());
    }

    @Test
    void getDetail() {
        Recipe recipe = new Recipe();
        String detail = "hohoho";
        recipe.setDetail(detail);
        assertNotNull(recipe.getDetail());
    }

    @Test
    void setDetail() {
        Recipe recipe = new Recipe();
        String detail = "hohoho";
        recipe.setDetail(detail);
        assertEquals(detail, recipe.getDetail());
    }
}