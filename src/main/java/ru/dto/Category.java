package ru.dto;

import lombok.*;

import java.util.ArrayList;

@Data
public class Category {
    Integer id;
    String title;
    ArrayList<Product> products;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
