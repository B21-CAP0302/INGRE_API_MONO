package com.pascal7.ingre_api_mono.service;

import com.pascal7.ingre_api_mono.custom.IngredientDto;
import com.pascal7.ingre_api_mono.custom.RecipeDto;
import com.pascal7.ingre_api_mono.entity.*;
import com.pascal7.ingre_api_mono.repository.RecipeRepository;
import com.pascal7.ingre_api_mono.utils.BankString;
import com.pascal7.ingre_api_mono.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    TxIngredientRecipeServiceImpl txIngredientRecipeService;

    @Autowired
    IngredientService ingredientService;

    @Autowired
    Helper helper;

    @Autowired
    ImageEntityService imageEntityService;

    @Override
    public RecipeDto create(RecipeDto recipeDto) {
        helper.validateIdIsNull(recipeDto.getId());
        Recipe recipe = recipeRepository.save(new Recipe(recipeDto));
        saveTxIngredientRecipe(recipeDto, recipe);
        recipeDto.setId(recipe.getId());
        recipeDto.setIngredients(recipeDto.getIngredients()
                .stream()
                .map(
                    ingredientDto -> {
                        validateQtyIsNotMinus(ingredientDto.getQty());
                        return new IngredientDto(
                                ingredientService
                                        .getById(
                                                ingredientDto
                                                        .getIngredientId()),
                                ingredientDto
                                        .getQty()
                        );
                    }
        ).collect(Collectors.toList()));
        return recipeDto;
    }

    private void validateQtyIsNotMinus(Integer qty) {
        if(qty <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "qty is not valid");
        }
    }

    private void saveTxIngredientRecipe(RecipeDto recipeDto, Recipe recipe) {
        recipeDto.getIngredients()
                .stream()
                .peek(
                    ingredientDto -> {
                        ingredientService.getById(ingredientDto.getIngredientId());
                        validateQtyIsNotMinus(ingredientDto.getQty());
                    })
                .forEach(ingredientDto -> txIngredientRecipeService.create(
                    new TxIngredientRecipe(
                            recipe,
                            ingredientService.getById(ingredientDto.getIngredientId()),
                            ingredientDto.getQty())
                    )
                );
    }

    @Override
    public RecipeDto getById(String id) {
        validateIdIsExist(id);
        Recipe recipe = recipeRepository.getById(id);
        return new RecipeDto(recipe, txIngredientRecipeService.getByRecipe(recipe));
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
        deleteIngredientList(recipe);
        recipeRepository.delete(recipe);
        throw new ResponseStatusException(HttpStatus.ACCEPTED, String.format(BankString.idDeleteFormat, id));
    }

    @Override
    public List<RecipeDto> recipeByCategory(String category) {
        List<RecipeDto> recipeDtos = new ArrayList<>();
        recipeRepository.findByCategory(category).forEach(
                recipe -> {
                    recipeDtos.add(new RecipeDto(
                            recipe,
                            txIngredientRecipeService.getByRecipe(recipe))
                    );
                }
        );
        return recipeDtos;
    }

    @Override
    public RecipeDto createWithFile(RecipeDto recipeDto, MultipartFile multipartFile) throws IOException {
        recipeDto = create(recipeDto);
        buildRecipe(recipeDto, multipartFile);
        return update(recipeDto);
    }

    @Override
    public RecipeDto updateWithFile(RecipeDto recipeDto, MultipartFile multipartFile) throws IOException {
        recipeDto = update(recipeDto);
        buildRecipe(recipeDto, multipartFile);
        return update(recipeDto);
    }

    private void buildRecipe(RecipeDto recipe, MultipartFile multipartFile) throws IOException {
        if(multipartFile != null){
            setRecipeWithFile(recipe, multipartFile);
        } else {
            setRecipeWithoutFile(recipe);
        }
    }

    private void setRecipeWithFile(RecipeDto recipe, MultipartFile multipartFile) throws IOException {
        if(!multipartFile.isEmpty()){
            imageEntityService.addMultipartFile(recipe.getId(), multipartFile);
            recipe.setPhoto(BankString.fileApi + recipe.getId());
        }
    }

    private void setRecipeWithoutFile(RecipeDto recipe) {
        Optional<ImageEntity> imageEntity = imageEntityService.getByIdOptional(recipe.getId());
        imageEntity.ifPresent(entity -> imageEntityService.delete(entity.getId()));
        recipe.setPhoto(null);
    }
}