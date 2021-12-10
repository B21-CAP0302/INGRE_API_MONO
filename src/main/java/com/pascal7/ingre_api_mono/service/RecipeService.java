package com.pascal7.ingre_api_mono.service;

import com.pascal7.ingre_api_mono.custom.RecipeDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RecipeService extends CRUDServiceTemplate<RecipeDto>{
    List<RecipeDto> recipeByCategory(String category);
    RecipeDto createWithFile(RecipeDto recipeDto, MultipartFile multipartFile) throws IOException;
    RecipeDto updateWithFile(RecipeDto recipeDto, MultipartFile multipartFile) throws IOException;
}
