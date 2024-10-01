package com.ms.shopping_api.dto;

import com.ms.shopping_api.model.ShopModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public class ShopDTO2 {

    @NotBlank
    private String userIdentifier;
    @NotNull
    private Float total;
    @NotNull
    private Date date;
    @NotNull
    private List<ItemDTO> items;
    // get and sets
    public static ShopDTO2 convert(ShopModel shopModel) {
        ShopDTO2 shopDTO = new ShopDTO2();
        shopDTO.setUserIdentifier(shopModel.getUserIdentifier());
        shopDTO.setTotal(shopModel.getTotal());
        return shopDTO;
    }

    public @NotBlank String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(@NotBlank String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public @NotNull Float getTotal() {
        return total;
    }

    public void setTotal(@NotNull Float total) {
        this.total = total;
    }

    public @NotNull Date getDate() {
        return date;
    }

    public void setDate(@NotNull Date date) {
        this.date = date;
    }

    public @NotNull List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(@NotNull List<ItemDTO> items) {
        this.items = items;
    }
}
