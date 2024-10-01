package com.ms.shopping_api.dto;

import com.ms.shopping_api.model.ItemModel;
import com.ms.shopping_api.model.ShopModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public record ShopDTO(@NotNull long idShop,
                      @NotBlank String userIdentifier,
                      @NotNull float total,
                      Date date,
                      List<ItemModel> items) {

}
