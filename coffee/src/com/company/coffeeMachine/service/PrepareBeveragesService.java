package com.company.coffeeMachine.service;

import com.company.coffeeMachine.customException.NotAvailableException;
import com.company.coffeeMachine.dao.ResourceManagerSingleton;
import com.company.coffeeMachine.model.Beverage;

import java.util.HashMap;

public class PrepareBeveragesService {

    private static ResourceManagerSingleton coffeeMachineDaoSingleton = ResourceManagerSingleton.getInstance();

    public static void getBeverages(String beverage){
        HashMap<String, Beverage> beverageHashMap = coffeeMachineDaoSingleton.getBeverages();

        // first check the beverage is present or not
        if(!beverageHashMap.containsKey(beverage)){
            throw new NotAvailableException("Beverage Not Available");
        }

        Beverage bev = beverageHashMap.get(beverage);

        // run Thread
        Runnable r = new Task( bev );
        TaskExecutor.getCommonExecutor().execute(r);
    }

    // Task 
    static class Task implements Runnable{

        private Beverage beverage;
        public Task(Beverage beverage){
            this.beverage = beverage;
        }
        @Override
        public void run() {
            try{
                String outlet = coffeeMachineDaoSingleton.updateIngredients(beverage);

                // for testing
                Thread.sleep(10000);

                System.out.println(outlet);
            }catch (NotAvailableException | InterruptedException e){
                System.out.println(e.getMessage());
            }

        }
    }

}
