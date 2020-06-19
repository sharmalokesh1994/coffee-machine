package com.company.coffeeMachine.dao;


import com.company.coffeeMachine.customException.NotAvailableException;
import com.company.coffeeMachine.model.Beverage;
import com.company.coffeeMachine.model.Ingredient;

import java.util.HashMap;

//todo: we will create an interface for this and implement that in the class
// when we create DB and connect with the Schema
// Here we will create this class as Singleton class
public class ResourceManagerSingleton {

    private static ResourceManagerSingleton instance;
    private HashMap<String, Beverage> beverages;
    private HashMap<String, Ingredient> ingredients;

    private int outlets;
    public static int countOutlets = 1;

    private ResourceManagerSingleton(){
        beverages = new HashMap<>();
        ingredients = new HashMap<>();
    }

    public void addIngredients(Ingredient ingredient){
        ingredients.put(ingredient.getName(),ingredient);
    }

    public void addBeverage(Beverage beverage){
        beverages.put(beverage.getName(),beverage);
    }

    public HashMap<String, Beverage> getBeverages() {
        return beverages;
    }

    public HashMap<String, Ingredient> getIngredients() {
        return ingredients;
    }

    public int getOutlets() {
        return outlets;
    }

    public void setOutlets(int outlets) {
        this.outlets = outlets;
    }

    // To make it Thread-safe used synchronized
    public synchronized String updateIngredients(Beverage beverage){
        HashMap<String, Integer> requiredIngredients =beverage.getIngredients();

        // check all the Ingredients
        for(String str : requiredIngredients.keySet()){
            if( !ingredients.containsKey(str) ){
                throw new NotAvailableException(beverage.getName()+ " cannot be prepared because " +str + " is not available");
            }
            // todo: Auto filling is happening
            /*else if( requiredIngredients.get(str)>ingredients.get(str).getQuantity() ){
                throw new NotAvailableException(beverage.getName()+ " cannot be prepared because " +str + " is not sufficient");
            }
            ingredients.get(str).updateIngredients(-requiredIngredients.get(str));*/
        }

        // updating the Ingredients
        for(String str : requiredIngredients.keySet()){
            //ingredients.get(str).updateIngredients(-requiredIngredients.get(str));
        	updateIngredient(str,-requiredIngredients.get(str));
        }

        countOutlets++;
        return beverage.getName()+" is prepared get it from outlet no : "+ ((countOutlets%outlets)+1);
    }
    
    public synchronized void updateIngredient(String ing, int value){
    	// used synchronized for thread-safe
        Ingredient ingrediant = ingredients.get(ing); 
        int quantity = ingrediant.getQuantity() + value;
        if(quantity<0){
        	System.out.println("Auto filling is going on "+ingrediant.getName());
            // fully Auto-filed happened
            // todo: Auto-fill functionality
            ingrediant.setQuantity(ingrediant.getMaxLimit()+quantity);
        }
        else{
        	ingrediant.setQuantity(quantity);
        }
        

    }


    public static ResourceManagerSingleton getInstance(){

        if(instance==null){
            synchronized (ResourceManagerSingleton.class){
                if(instance==null){
                    instance = new ResourceManagerSingleton();
                }
            }
        }

        return instance;
    }

}
