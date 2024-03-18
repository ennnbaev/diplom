package com.example.demo.item.dto;

import org.springframework.stereotype.Component;

@Component
public class ItemOffer {
    private String itemId;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
