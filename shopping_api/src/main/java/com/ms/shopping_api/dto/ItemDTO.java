package com.ms.shopping_api.dto;

import com.ms.shopping_api.model.ItemModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemDTO(@NotBlank String productIdentifier,
                      @NotNull Float price) {

    public static ItemDTO convert(ItemModel item) {
        return new ItemDTO(
        item.getProductIdentifier(),
        item.getPrice());
    }

}
