package com.example.demo.item.dto;

import org.springframework.stereotype.Component;

@Component
public class ItemDto {
    private Long id;
    private String name;
    private String description;
    private String price;
    private String category;
    private String wantedItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWantedItem() {
        return wantedItem;
    }

    public void setWantedItem(String wantedItem) {
        this.wantedItem = wantedItem;
    }
}
