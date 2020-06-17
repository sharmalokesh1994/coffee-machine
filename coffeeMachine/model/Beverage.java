package com.company.LLD.coffeeMachine.model;

import com.company.LLD.coffeeMachine.customException.NotAvailableException;
import com.company.LLD.coffeeMachine.dao.CoffeeMachineDaoSingleton;

import java.util.HashSet;

public class Beverage {

    // todo:we are considering name as unique id
    private String name;
    private HashSet<BeverageIngredient> ingredients;

    public Beverage(String name) {
        setName(name);
        ingredients = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public HashSet<BeverageIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(HashSet<BeverageIngredient> ingredients) {
        this.ingredients = ingredients;
    }


    public  String getBeverage(){
        synchronized(Beverage.class) {
            // chaining
            for (BeverageIngredient ingredient : ingredients) {
                if (!CoffeeMachineDaoSingleton.getInstance().getIngredients().containsKey(ingredient.getIngredient())) {
                    return name + " cannot be prepared because " + ingredient.getIngredient() + " is not available";
                }

                Ingredient ingredient1 = CoffeeMachineDaoSingleton.getInstance().getIngredients().get(ingredient.getIngredient());
                if (ingredient1.getIngredientForBeverage(-ingredient.getQuantity()) == false) {
                    //throw new NotAvailableException(name+" cannot be prepared because "+ingredient.getIngredient().getName()+" is not available");
                    return name + " cannot be prepared because " + ingredient.getIngredient() + " is not sufficient";
                }
            }
        }
        return name+" is prepared";
    }

}
