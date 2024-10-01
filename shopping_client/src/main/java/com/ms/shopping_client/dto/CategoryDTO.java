package com.ms.shopping_client.dto;

import com.ms.product_api.model.CategoryModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryDTO(@NotBlank String name, @NotNull Long idCategory) {

    public static CategoryDTO convert(CategoryModel category) {
        return new CategoryDTO(
                category.getName(),
                category.getIdCategory()
        );
    }


}
