package com.ms.product_api.converter;

import com.ms.product_api.dto.CategoryDTO;
import com.ms.product_api.dto.ProductDTO;
import com.ms.product_api.model.CategoryModel;
import com.ms.product_api.model.ProductModel;

public class DTOConverter {

    public static CategoryDTO convert(CategoryModel category) {
        return new CategoryDTO(
                category.getName(),
                category.getIdCategory());
    }

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
