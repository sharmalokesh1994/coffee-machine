package com.company.coffeeMachine.model;


import java.util.HashMap;

public class Beverage {

    // todo:we are considering name as unique id
    private String name;
    private HashMap<String,Integer> ingredients;

    public Beverage(String name) {
        setName(name);
        ingredients = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(HashMap<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }

}
