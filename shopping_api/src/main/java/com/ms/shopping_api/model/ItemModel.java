package com.ms.shopping_api.model;

import com.ms.shopping_api.dto.ItemDTO;
import jakarta.persistence.Embeddable;

@Embeddable
public class ItemModel {

    private String productIdentifier;
    private Float price;


    public static ItemModel convert(ItemDTO itemDTO) {
        ItemModel item = new ItemModel();
        item.setProductIdentifier(itemDTO.productIdentifier());
        item.setPrice(itemDTO.price());
        return item;
    }
    public String getProductIdentifier() {
        return productIdentifier;
    }

    public void setProductIdentifier(String productIdentifier) {
        this.productIdentifier = productIdentifier;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}