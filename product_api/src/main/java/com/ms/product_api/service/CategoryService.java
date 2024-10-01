package com.ms.product_api.service;

import com.ms.product_api.dto.CategoryDTO;
import com.ms.product_api.model.CategoryModel;
import com.ms.product_api.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public ResponseEntity<CategoryModel> saveCategory(CategoryDTO categoryDTO){
        var categoryModel = new CategoryModel();
        BeanUtils.copyProperties(categoryDTO, categoryModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryRepository.save(categoryModel));
    }

    public ResponseEntity <List<CategoryModel>> getAllCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryRepository.findAll());
    }

    public ResponseEntity<Object> getCategiryById(Long idCategory){
        Optional<CategoryModel> categoryModel = categoryRepository.findById(idCategory);
        if(categoryModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body(categoryModel.get());
    }

    public ResponseEntity<Object> updateCategory(Long id, CategoryDTO categoryDTO) {
        Optional<CategoryModel> categoryObject = categoryRepository.findById(id);
        if (categoryObject.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
        var categoryModel = categoryObject.get();
        BeanUtils.copyProperties(categoryDTO, categoryModel);
        return ResponseEntity.status(HttpStatus.OK).body(categoryRepository.save(categoryModel));
    }

    public ResponseEntity<Object> deleteCategory(Long idCategory){
        Optional<CategoryModel> categoryObject = categoryRepository.findById(idCategory);
        if(categoryObject.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found!");
        }
        categoryRepository.delete(categoryObject.get());
        return ResponseEntity.status(HttpStatus.OK).body("Category delected successfully");
    }
}
