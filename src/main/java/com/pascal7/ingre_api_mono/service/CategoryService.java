package com.pascal7.ingre_api_mono.service;

import com.pascal7.ingre_api_mono.entity.Category;
import com.pascal7.ingre_api_mono.repository.CategoryRepository;
import com.pascal7.ingre_api_mono.utils.BankString;
import com.pascal7.ingre_api_mono.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    Helper helper;

    public Category create(Category category) {
        helper.validateIdIsNull(category.getId());
        return categoryRepository.save(category);
    }

    public Category getById(String id) {
        validateIdIsExist(id);
        return categoryRepository.getById(id);
    }

    private void validateIdIsExist(String id) {
        if(!categoryRepository.findById(id).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BankString.idDidNotExist);
        }
    }

    public Category update(Category category) {
        validateIdIsExist(category.getId());
        return categoryRepository.save(category);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public void delete(String id) {
        categoryRepository.delete(getById(id));
        throw new ResponseStatusException(HttpStatus.ACCEPTED, BankString.success);
    }
}
