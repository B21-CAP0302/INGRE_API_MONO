package com.pascal7.ingre_api_mono.entity;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class IngredientEntityTest {

    @Test
    void getId() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId("123");
        assertNotNull(ingredient.getId());
    }

    @Test
    void setId() {
        Ingredient ingredient = new Ingredient();
        String id = "321";
        ingredient.setId(id);
        assertEquals(id, ingredient.getId());
    }

    @Test
    void getUnit() {
        Ingredient ingredient = new Ingredient();
        ingredient.setUnit("kg");
        assertNotNull(ingredient.getUnit());
    }

    @Test
    void setUnit() {
        Ingredient ingredient = new Ingredient();
        String unit = "helai";
        ingredient.setUnit(unit);
        assertEquals(unit, ingredient.getUnit());
    }

    @Test
    void getName() {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("anu");
        assertNotNull(ingredient.getName());
    }

    @Test
    void setName() {
        Ingredient ingredient = new Ingredient();
        String name = "putra";
        ingredient.setName(name);
        assertEquals(name, ingredient.getName());
    }

    @Test
    void getStock() {
        Ingredient ingredient = new Ingredient();
        ingredient.setStock(10);
        assertNotNull(ingredient.getStock());
    }

    @Test
    void setStock() {
        Ingredient ingredient = new Ingredient();
        Integer stock = 20;
        ingredient.setStock(stock);
        assertNotNull(ingredient.getStock());
    }

    @Test
    void getPrice() {
        Ingredient ingredient = new Ingredient();
        ingredient.setPrice(2000);
        assertNotNull(ingredient.getPrice());
    }

    @Test
    void setPrice() {
        Ingredient ingredient = new Ingredient();
        Integer price = 4000;
        ingredient.setPrice(price);
        assertEquals(price, ingredient.getPrice());
    }

    @Test
    void getDate() {
        Ingredient ingredient = new Ingredient();
        ingredient.setDate(new Timestamp(System.currentTimeMillis()));
        assertNotNull(ingredient.getDate());
    }

    @Test
    void setDate() {
        Ingredient ingredient = new Ingredient();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ingredient.setDate(timestamp);
        assertEquals(timestamp, ingredient.getDate());
    }
}