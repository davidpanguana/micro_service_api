package com.ms.product_api.controller;

import com.ms.product_api.dto.CategoryDTO;
import com.ms.product_api.model.CategoryModel;
import com.ms.product_api.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<CategoryModel> saveCategory(@RequestBody @Valid CategoryDTO categoryDTO){
        return categoryService.saveCategory(categoryDTO);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryModel>> getALL(){
        return categoryService.getAllCategory();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Object>getCategoryById(@PathVariable(value = "id") Long id){
        return categoryService.getCategiryById(id);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable(value = "id") Long id,
                                             @RequestBody @Valid CategoryDTO categoryDTO){

        return categoryService.updateCategory(id, categoryDTO);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable(value = "id") Long id){
        return categoryService.deleteCategory(id);
    }


}
