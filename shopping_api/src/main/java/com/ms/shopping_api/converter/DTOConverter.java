package com.ms.shopping_api.converter;

import com.ms.shopping_api.dto.ItemDTO;
import com.ms.shopping_api.dto.ShopDTO2;
import com.ms.shopping_api.model.ItemModel;
import com.ms.shopping_api.model.ShopModel;

import java.util.stream.Collectors;

public class DTOConverter {

    public static ItemDTO convert(ItemModel item) {
        return new ItemDTO(
        item.getProductIdentifier(),
        item.getPrice());
    }

    public static ShopDTO2 convert(ShopModel shop) {
        ShopDTO2 shopDTO = new ShopDTO2();
        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setTotal(shop.getTotal());
        shopDTO.setDate(shop.getDate());
        shopDTO.setItems(shop
                .getItems()
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList()));
        return shopDTO;
    }
}
