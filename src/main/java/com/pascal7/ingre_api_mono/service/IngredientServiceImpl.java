package com.pascal7.ingre_api_mono.service;

import com.pascal7.ingre_api_mono.entity.Ingredient;
import com.pascal7.ingre_api_mono.repository.IngredientRepository;
import com.pascal7.ingre_api_mono.utils.BankString;
import com.pascal7.ingre_api_mono.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService{

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    Helper helper;

    @Override
    public Ingredient create(Ingredient ingredient) {
        helper.validateIdIsNull(ingredient.getId());
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient getById(String id) {
        helper.validateIdIsNotNull(id);
        validateIdIsExist(id);
        return ingredientRepository.getById(id);
    }

    private void validateIdIsExist(String id) {
        if(!ingredientRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BankString.idDidNotExist);
        }
    }

    private void validateIdIsNotExist(String id) {
        if(ingredientRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BankString.idIsExist);
        }
    }

    @Override
    public Ingredient update(Ingredient ingredient) {
        helper.validateIdIsNotNull(ingredient.getId());
        validateIdIsExist(ingredient.getId());
        ingredient.setDate(getById(ingredient.getId()).getDate());
        return ingredientRepository.save(ingredient);
    }

    @Override
    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public void delete(String id) {
        ingredientRepository.delete(getById(id));
        throw new ResponseStatusException(HttpStatus.ACCEPTED, "success");
    }
}
