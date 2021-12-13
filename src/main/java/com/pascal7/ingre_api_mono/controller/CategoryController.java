package com.pascal7.ingre_api_mono.controller;

import com.pascal7.ingre_api_mono.custom.CustomPage;
import com.pascal7.ingre_api_mono.entity.Category;
import com.pascal7.ingre_api_mono.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/api/product/categories")
    public List<Category> getCategories(){
        return categoryService.getAll();
    }

    @PostMapping("/api/product/category")
    public Category create(@RequestBody Category category){
        return categoryService.create(category);
    }

    @PutMapping("/api/product/category")
    public Category update(@RequestBody Category category){
        return categoryService.update(category);
    }

    @DeleteMapping("/api/product/category/{id}")
    public void delete(@PathVariable String id){
        categoryService.delete(id);
    }

}
