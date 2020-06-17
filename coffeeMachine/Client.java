package com.company.LLD.coffeeMachine;

import com.company.LLD.coffeeMachine.dao.CoffeeMachineDaoSingleton;
import com.company.LLD.coffeeMachine.model.Beverage;
import com.company.LLD.coffeeMachine.model.BeverageIngredient;
import com.company.LLD.coffeeMachine.service.PrepareBeverages;
import com.company.LLD.coffeeMachine.service.ReadJSON;

import java.util.HashMap;
import java.util.HashSet;

public class Client {

    public static void main(String[] args) {
        ReadJSON.setDAO("D:\\Desktop\\AEM_Training\\aem_projects\\java_fx\\java\\GeeksForGeeksTest\\src\\test1.json");
        PrepareBeverages.getBeverages();

        /*HashMap<String, Beverage> hashMap= CoffeeMachineDaoSingleton.getInstance().getBeverages();

        for( String str : hashMap.keySet() ){
            System.out.println(str+" : ");

            HashSet<BeverageIngredient> hashSet = hashMap.get(str).getIngredients();
            for(  BeverageIngredient t : hashSet ){
                System.out.println(t.getIngredient());
            }
        }*/
    }

}
