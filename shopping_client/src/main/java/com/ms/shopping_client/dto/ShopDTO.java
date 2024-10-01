package com.ms.shopping_client.dto;

import com.ms.shopping_api.model.ItemModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public record ShopDTO(@NotNull long idShop,
                      @NotBlank String userIdentifier,
                      @NotNull float total,
                      Date date,
                      List<ItemModel> items) {

}
