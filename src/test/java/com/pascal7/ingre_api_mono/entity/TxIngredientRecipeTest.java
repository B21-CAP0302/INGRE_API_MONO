package com.pascal7.ingre_api_mono.entity;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class TxIngredientRecipeTest {

    @Test
    void constructor1(){
        TxIngredientRecipe txIngredientRecipe = new TxIngredientRecipe();
        assertNotNull(txIngredientRecipe);
    }

    @Test
    void constructor2(){
        TxIngredientRecipe txIngredientRecipe = new TxIngredientRecipe(new Recipe(), new Ingredient(), 1);
        assertNotNull(txIngredientRecipe);
    }

    @Test
    void getId() {
        TxIngredientRecipe txIngredientRecipe = new TxIngredientRecipe();
        String id = "3242rwef23";
        ReflectionTestUtils.setField(txIngredientRecipe, "id", id);
        assertNotNull(txIngredientRecipe.getId());
    }

    @Test
    void setId() {
        TxIngredientRecipe txIngredientRecipe = new TxIngredientRecipe();
        String id = "3242rwef23";
        ReflectionTestUtils.setField(txIngredientRecipe, "id", id);
        assertEquals(id, txIngredientRecipe.getId());
    }

    @Test
    void getRecipe() {
        TxIngredientRecipe txIngredientRecipe = new TxIngredientRecipe();
        Recipe recipe = new Recipe();
        txIngredientRecipe.setRecipe(recipe);
        assertNotNull(txIngredientRecipe.getRecipe());
    }

    @Test
    void setRecipe() {
        TxIngredientRecipe txIngredientRecipe = new TxIngredientRecipe();
        Recipe recipe = new Recipe();
        txIngredientRecipe.setRecipe(recipe);
        assertEquals(recipe, txIngredientRecipe.getRecipe());
    }

    @Test
    void getIngredient() {
        TxIngredientRecipe txIngredientRecipe = new TxIngredientRecipe();
        Ingredient ingredient = new Ingredient();
        txIngredientRecipe.setIngredient(ingredient);
        assertNotNull(txIngredientRecipe.getIngredient());
    }

    @Test
    void setIngredient() {
        TxIngredientRecipe txIngredientRecipe = new TxIngredientRecipe();
        Ingredient ingredient = new Ingredient();
        txIngredientRecipe.setIngredient(ingredient);
        assertEquals(ingredient, txIngredientRecipe.getIngredient());
    }

    @Test
    void getQuantity() {
        TxIngredientRecipe txIngredientRecipe = new TxIngredientRecipe();
        Integer quantity = 10;
        txIngredientRecipe.setQuantity(quantity);
        assertNotNull(txIngredientRecipe.getQuantity());
    }

    @Test
    void setQuantity() {
        TxIngredientRecipe txIngredientRecipe = new TxIngredientRecipe();
        Integer quantity = 10;
        txIngredientRecipe.setQuantity(quantity);
        assertEquals(quantity, txIngredientRecipe.getQuantity());
    }

    @Test
    void getRecipeId(){
        TxIngredientRecipe txIngredientRecipe = new TxIngredientRecipe();
        String id = "21f23rsf";
        txIngredientRecipe.setRecipeId(id);
        assertNotNull(txIngredientRecipe.getRecipeId());
    }

    @Test
    void setRecipeId(){
        TxIngredientRecipe txIngredientRecipe = new TxIngredientRecipe();
        String id = "21f23rsf";
        txIngredientRecipe.setRecipeId(id);
        assertEquals(id, txIngredientRecipe.getRecipeId());
    }

    @Test
    void getIngredientId(){
        TxIngredientRecipe txIngredientRecipe = new TxIngredientRecipe();
        String id = "21f23rsf";
        txIngredientRecipe.setIngredientId(id);
        assertNotNull(txIngredientRecipe.getIngredientId());
    }

    @Test
    void setIngredientId(){
        TxIngredientRecipe txIngredientRecipe = new TxIngredientRecipe();
        String id = "21f23rsf";
        txIngredientRecipe.setIngredientId(id);
        assertEquals(id, txIngredientRecipe.getIngredientId());
    }
}