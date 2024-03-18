package com.example.demo.item.domain;


import com.example.demo.user.domain.User;
import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double price;
    private String category;
    private String name;
    private String description;
    @Column(name = "wanted_item")
    private String wantedItem;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getWantedItem() {
        return wantedItem;
    }

    public void setWantedItem(String wantedItem) {
        this.wantedItem = wantedItem;
    }
}
