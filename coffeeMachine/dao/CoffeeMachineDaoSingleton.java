package com.company.LLD.coffeeMachine.dao;


import com.company.LLD.coffeeMachine.model.Beverage;
import com.company.LLD.coffeeMachine.model.Ingredient;
import com.company.LLD.coffeeMachine.model.Outlet;

import java.util.HashMap;

//todo: we will create an interface for this and implement that in the class
// when we create DB and connect with the Schema
// Here we will create this class as Singleton class
public class CoffeeMachineDaoSingleton {

    private static CoffeeMachineDaoSingleton instance;

    private HashMap<Integer, Outlet> outlets;
    private HashMap<String, Beverage> beverages;
    private HashMap<String,Ingredient> ingredients;

    private CoffeeMachineDaoSingleton(){
        outlets = new HashMap<>();
        beverages = new HashMap<>();
        ingredients = new HashMap<>();
    }

    public void addOutlets(Outlet outlet){
        outlets.put(outlet.getuID(),outlet);
    }

    public void addIngredients(Ingredient ingredient){
        ingredients.put(ingredient.getName(),ingredient);
    }

    public void addBeverage(Beverage beverage){
        beverages.put(beverage.getName(),beverage);
    }

    public HashMap<Integer, Outlet> getOutlets() {
        return outlets;
    }

    public HashMap<String, Beverage> getBeverages() {
        return beverages;
    }

    public HashMap<String, Ingredient> getIngredients() {
        return ingredients;
    }

    public static CoffeeMachineDaoSingleton getInstance(){

        if(instance==null){
            synchronized (CoffeeMachineDaoSingleton.class){
                if(instance==null){
                    instance = new CoffeeMachineDaoSingleton();
                }
            }
        }

        return instance;
    }

}
