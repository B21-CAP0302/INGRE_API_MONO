package com.pascal7.ingre_api_mono.service;

import com.pascal7.ingre_api_mono.custom.IngredientDto;
import com.pascal7.ingre_api_mono.entity.Recipe;
import com.pascal7.ingre_api_mono.entity.TxIngredientRecipe;
import com.pascal7.ingre_api_mono.repository.TxIngredientRecipeRepository;
import com.pascal7.ingre_api_mono.utils.BankString;
import com.pascal7.ingre_api_mono.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TxIngredientRecipeServiceImpl implements CRUDServiceTemplate<TxIngredientRecipe> {

    @Autowired
    TxIngredientRecipeRepository txIngredientRecipeRepository;

    @Autowired
    IngredientService ingredientService;

    @Autowired
    Helper helper;

    @Override
    public TxIngredientRecipe create(TxIngredientRecipe txIngredientRecipe) {
        helper.validateIdIsNull(txIngredientRecipe.getId());
        ingredientService.getById(txIngredientRecipe.getIngredient().getId());
        return txIngredientRecipeRepository.save(txIngredientRecipe);
    }

    @Override
    public TxIngredientRecipe getById(String id) {
        validateIdIsExist(id);
        return txIngredientRecipeRepository.getById(id);
    }

    private void validateIdIsExist(String id) {
        if(!txIngredientRecipeRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BankString.idDidNotExist);
        }
    }

    public List<TxIngredientRecipe> getByRecipe(Recipe recipe) {
        validateRecipeIdIsExist(recipe);
        return txIngredientRecipeRepository.findByRecipe(recipe).get();
    }

    private void validateRecipeIdIsExist(Recipe recipe) {
        if(!txIngredientRecipeRepository.findByRecipe(recipe).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BankString.idDidNotExist);
        }
    }

    @Override
    public TxIngredientRecipe update(TxIngredientRecipe txIngredientRecipe) {
        validateIdIsExist(txIngredientRecipe.getId());
        return txIngredientRecipeRepository.save(txIngredientRecipe);
    }

    @Override
    public List<TxIngredientRecipe> getAll() {
        return txIngredientRecipeRepository.findAll();
    }

    @Override
    public void delete(String id) {
        txIngredientRecipeRepository.delete(getById(id));
    }
}
