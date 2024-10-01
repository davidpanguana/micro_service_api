package com.ms.product_api.model;

import com.ms.product_api.dto.CategoryDTO;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "TB_CATEGORY")
public class CategoryModel {

    public static CategoryModel convert(CategoryDTO categoryDTO) {
        CategoryModel category = new CategoryModel();
        category.setIdCategory(categoryDTO.idCategory());
        category.setName(categoryDTO.name());
        return category;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;
    private String name;

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
