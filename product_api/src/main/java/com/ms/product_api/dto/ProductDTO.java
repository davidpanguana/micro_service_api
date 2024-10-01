package com.ms.product_api.dto;

import com.ms.product_api.model.CategoryModel;
import com.ms.product_api.model.ProductModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductDTO(@NotBlank String name, @NotNull BigDecimal price,
                         @NotBlank String description, @NotBlank String productIdentifier,
                         @NotNull CategoryDTO category){

    public static ProductDTO convert(ProductModel product) {
        return new ProductDTO(
                product.getName(),
                product.getPrice(),
                product.getProductIdentifier(),
                product.getDescription(),
                CategoryDTO.convert(product.getCategory())
        );
    }
}
