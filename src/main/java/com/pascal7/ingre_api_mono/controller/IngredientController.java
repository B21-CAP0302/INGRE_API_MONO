package com.pascal7.ingre_api_mono.controller;

import com.pascal7.ingre_api_mono.entity.Ingredient;
import com.pascal7.ingre_api_mono.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    @PostMapping("/api/admin/product/ingredient")
    public Ingredient postIngredient(@RequestBody Ingredient ingredient){
        ingredient.setDate(new Timestamp(System.currentTimeMillis()));
        return ingredientService.create(ingredient);
    }

    @GetMapping("/api/product/ingredient")
    public List<Ingredient> getAllIngredient(){
        return ingredientService.getAll();
    }

    @GetMapping("/api/product/ingredient/{id}")
    public Ingredient getIngredient(@PathVariable String id){
        return ingredientService.getById(id);
    }

    @PutMapping("/api/admin/product/ingredient")
    public Ingredient updateIngredient(@RequestBody Ingredient ingredient){
        return ingredientService.update(ingredient);
    }

    @DeleteMapping("/api/admin/product/ingredient/{id}")
    public void deleteIngredient(@PathVariable String id){
        ingredientService.delete(id);
    }
}
