package com.pascal7.ingre_api_mono.service;

import com.pascal7.ingre_api_mono.custom.RecipeDto;
import com.pascal7.ingre_api_mono.entity.Recipe;
import com.pascal7.ingre_api_mono.entity.RecipeDetail;
import com.pascal7.ingre_api_mono.entity.TxIngredientRecipe;
import com.pascal7.ingre_api_mono.repository.RecipeRepository;
import com.pascal7.ingre_api_mono.utils.BankString;
import com.pascal7.ingre_api_mono.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    TxIngredientRecipeServiceImpl txIngredientRecipeService;

    @Autowired
    Helper helper;

    @Autowired
    RecipeDetailServiceImpl recipeDetailService;

    @Override
    public RecipeDto create(RecipeDto recipeDto) {
        helper.validateIdIsNull(recipeDto.getId());
        Recipe recipe = recipeRepository.save(new Recipe(recipeDto));
        String recipeDetail = recipeDto.getRecipeDetail();
        recipeDetailService.create(getRecipeDetails(recipeDetail, recipe));
        saveTxIngredientRecipe(recipeDto, recipe);
        recipeDto.setId(recipe.getId());
        return recipeDto;
    }

    private void saveTxIngredientRecipe(RecipeDto recipeDto, Recipe recipe) {
        recipeDto.getIngredients().forEach(
                ingredientDto -> {
                    txIngredientRecipeService.create(
                            new TxIngredientRecipe(
                                    recipe,
                                    ingredientDto.getIngredient(),
                                    ingredientDto.getQty())
                    );
                }
        );
    }

    private List<RecipeDetail> getRecipeDetails(String recipeDetail, Recipe recipe) {
        List<RecipeDetail> recipeDetails = new ArrayList<>();
        for (int i = 0; i < Math.floorDiv(recipeDetail.length(), 255) + 1; i++) {
            String detail;
            if (recipeDetail.length() > 255){
                detail = recipeDetail.substring(255);
                recipeDetail =  recipeDetail.replace(detail, "");
            } else {
                detail = recipeDetail;
            }
            recipeDetails.add(new RecipeDetail(recipe, detail));
        }
        return recipeDetails;
    }

    @Override
    public RecipeDto getById(String id) {
        validateIdIsExist(id);
        Recipe recipe = recipeRepository.getById(id);
        StringBuilder detail = new StringBuilder();
        for (RecipeDetail recipeDetail: recipeDetailService.getByRecipe(recipe)) {
            detail.append(recipeDetail.getDetail());
        }
        return new RecipeDto(recipe, detail.toString(), txIngredientRecipeService.getByRecipe(recipe));
    }

    private void validateIdIsExist(String id) {
        if(!recipeRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BankString.idDidNotExist);
        }
    }

    @Override
    public RecipeDto update(RecipeDto recipeDto) {
        validateIdIsExist(recipeDto.getId());
        Recipe recipe = new Recipe(recipeDto);
        deleteRecipeDetail(recipe);
        String recipeDetail = recipeDto.getRecipeDetail();
        recipeDetailService.create(getRecipeDetails(recipeDetail, recipe));
        deleteIngredientList(recipe);
        saveTxIngredientRecipe(recipeDto, recipe);
        recipe.setDate(getById(recipe.getId()).getDate());
        recipeDto.setId(recipeRepository.save(recipe).getId());
        return recipeDto;
    }

    private void deleteIngredientList(Recipe recipe) {
        txIngredientRecipeService.getByRecipe(recipe).forEach(
                txIngredientRecipe -> {
                    txIngredientRecipeService.delete(txIngredientRecipe.getId());
                }
        );
    }

    private void deleteRecipeDetail(Recipe recipe) {
        recipeDetailService.getByRecipe(recipe).forEach(
                recipeDetail -> {
                    recipeDetailService.delete(recipeDetail.getId());
                }
        );
    }

    @Override
    public List<RecipeDto> getAll() {
        List<RecipeDto> recipeDtos = new ArrayList<>();
        recipeRepository.findAll().forEach(
                recipe -> {
                    recipeDtos.add(getById(recipe.getId()));
                }
        );
        return recipeDtos;
    }

    @Override
    public void delete(String id) {
        validateIdIsExist(id);
        Recipe recipe = new Recipe(getById(id));
        deleteRecipeDetail(recipe);
        deleteIngredientList(recipe);
        recipeRepository.delete(recipe);
        throw new ResponseStatusException(HttpStatus.ACCEPTED, String.format(BankString.idDeleteFormat, id));
    }
}