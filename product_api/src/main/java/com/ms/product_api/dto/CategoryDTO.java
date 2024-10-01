package com.ms.product_api.dto;

import com.ms.product_api.model.CategoryModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CategoryDTO(@NotBlank String name,@NotNull Long idCategory) {

    public static CategoryDTO convert(CategoryModel category) {
        return new CategoryDTO(
                category.getName(),
                category.getIdCategory()
        );
    }


}
