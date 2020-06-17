package com.company.LLD.coffeeMachine.model;

public class BeverageIngredient {

    // todo: create Unique id
    private String ingredient;
    private int quantity;

    public BeverageIngredient(String ingredient, int quantity) {

        setIngredient(ingredient);
        setQuantity(quantity);
    }

    public String getIngredient() {
        return ingredient;
    }

    // check one more
    public void setIngredient(String ingredient){

        this.ingredient = ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
