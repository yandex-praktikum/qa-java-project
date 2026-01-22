package org.example.models;

import java.util.List;

public class Order {
    public List<String> ingredients;

    public Order(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}