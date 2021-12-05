package com.pascal7.ingre_api_mono.service;

import com.pascal7.ingre_api_mono.entity.Recipe;
import com.pascal7.ingre_api_mono.entity.RecipeDetail;
import com.pascal7.ingre_api_mono.repository.RecipeDetailRepository;
import com.pascal7.ingre_api_mono.utils.BankString;
import com.pascal7.ingre_api_mono.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeDetailServiceImpl {

    @Autowired
    RecipeDetailRepository recipeDetailRepository;

    @Autowired
    Helper helper;

    public List<RecipeDetail> create(List<RecipeDetail> recipeDetails) {
        return recipeDetails
                .stream()
                .peek(recipeDetail -> {
                    helper.validateIdIsNull(recipeDetail.getId());
                    validateRecipeIdIsNotExist(recipeDetail.getRecipe());
                    recipeDetailRepository.save(recipeDetail);
                }).collect(Collectors.toList());
    }

    private void validateRecipeIdIsNotExist(Recipe recipe) {
        if(!recipeDetailRepository.findByRecipe(recipe).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BankString.idIsExist);
        }
    }

    private void validateRecipeIdIsExist(Recipe recipe) {
        if(!recipeDetailRepository.findByRecipe(recipe).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BankString.idDidNotExist);
        }
    }

    public List<RecipeDetail> getByRecipe(Recipe recipe) {
        validateRecipeIdIsExist(recipe);
        return recipeDetailRepository.findByRecipe(recipe).get();
    }

    public RecipeDetail getById(String id){
        validateIdDidExist(id);
        return recipeDetailRepository.getById(id);
    }

    private void validateIdDidExist(String id) {
        if(!recipeDetailRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BankString.idDidNotExist);
        }
    }

    public List<RecipeDetail> update(List<RecipeDetail> recipeDetails) {
        validateRecipeIdIsExist(recipeDetails.get(0).getRecipe());
        getByRecipe(recipeDetails.get(0).getRecipe())
                .forEach(recipeDetail -> {
                    validateIdDidExist(recipeDetail.getId());
                    delete(recipeDetail.getId());
                });
        return recipeDetails
                .stream().peek(recipeDetail -> {
                    recipeDetail.setId(null);
                    recipeDetailRepository.save(recipeDetail);
                }).collect(Collectors.toList());
    }

    public void delete(String id) {
        recipeDetailRepository.delete(getById(id));
    }
    public void delete(Recipe recipe) {
        recipeDetailRepository.deleteAll(getByRecipe(recipe));
    }
}
