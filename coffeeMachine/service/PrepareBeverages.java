package com.company.LLD.coffeeMachine.service;

import com.company.LLD.coffeeMachine.dao.CoffeeMachineDaoSingleton;
import com.company.LLD.coffeeMachine.model.Beverage;

import java.util.HashMap;

public class PrepareBeverages {

    private static CoffeeMachineDaoSingleton coffeeMachineDaoSingleton = CoffeeMachineDaoSingleton.getInstance();

    public static void getBeverages(){
        HashMap<String, Beverage> beverageHashMap = coffeeMachineDaoSingleton.getBeverages();

        for(String bev : beverageHashMap.keySet()){
            Runnable r = new Task( beverageHashMap.get(bev) );
            TaskExecutor.getCommonExecutor().execute(r);
        }
    }

    static class Task implements Runnable{

        private Beverage beverage;
        public Task(Beverage beverage){
            this.beverage = beverage;
        }
        @Override
        public void run() {
            String outlet = beverage.getBeverage();
            System.out.println(outlet);
        }
    }

}
