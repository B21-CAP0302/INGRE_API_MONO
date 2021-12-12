package com.pascal7.ingre_api_mono.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryEntityTest {

    @Test
    void getId() {
        Category category = new Category();
        category.setId("123");
        assertNotNull(category.getId());
    }

    @Test
    void getId_() {
        Category category = new Category();
        String id = "321";
        category.setId(id);
        assertEquals(id, category.getId());
    }

    @Test
    void getCategory() {
        Category category = new Category();
        category.setCategory("food");
        assertNotNull(category.getCategory());
    }

    @Test
    void getCategory_() {
        Category category = new Category();
        String categoryString = "drink";
        category.setCategory(categoryString);
        assertEquals(categoryString, category.getCategory());
    }

}