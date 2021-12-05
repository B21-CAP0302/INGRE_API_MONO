package com.pascal7.ingre_api_mono.controller;

import com.pascal7.ingre_api_mono.custom.RecipeDto;
import com.pascal7.ingre_api_mono.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @PostMapping("/api/admin/product/recipe")
    public RecipeDto postRecipe(@RequestBody RecipeDto recipeDto){
        recipeDto.setDate(new Timestamp(System.currentTimeMillis()));
        return recipeService.create(recipeDto);
    }

    @GetMapping("/api/product/recipe")
    public List<RecipeDto> getAllRecipe(){
        return recipeService.getAll();
    }

    @GetMapping("/api/product/recipe/{id}")
    public RecipeDto getRecipe(@PathVariable String id){
        return recipeService.getById(id);
    }

    @PutMapping("/api/admin/product/recipe")
    public RecipeDto updateRecipe(@RequestBody RecipeDto recipeDto){
        return recipeService.update(recipeDto);
    }

    @DeleteMapping("/api/admin/product/recipe/{id}")
    public void deleteRecipe(@PathVariable String id){
        recipeService.delete(id);
    }
}
