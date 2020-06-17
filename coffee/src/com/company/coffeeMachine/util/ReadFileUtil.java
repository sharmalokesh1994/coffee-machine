package com.company.coffeeMachine.util;

import com.company.coffeeMachine.dao.ResourceManagerSingleton;
import com.company.coffeeMachine.model.Beverage;
import com.company.coffeeMachine.model.Ingredient;
import com.company.coffeeMachine.service.TaskExecutor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// for reading the file and saving the data in the input
public class ReadFileUtil {

    private static ResourceManagerSingleton coffeeMachineDaoSingleton = ResourceManagerSingleton.getInstance();
    public static void setDAO(){

        String fileName = "testout.txt";
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(fileName));
            String outlets = br.readLine();
            setOutlet(outlets);

            String ing = br.readLine();
            setIngredients(ing);

            String beverages = br.readLine();
            setBeverage(beverages);

        }catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }

    public static void setOutlet(String outlets){

        int outlet = Integer.parseInt(outlets.split("=")[1].trim());
        coffeeMachineDaoSingleton.setOutlets(outlet);
        // Start thread Executor
        TaskExecutor.setCommonExecutor(outlet);

        //System.out.println(outlets.get("count_n").getClass());

    }

    public static void setIngredients(String ingredientsString){
        // considering max limit is 1000 for every ingredients

        String[] ingredients = ingredientsString.split(",");

        for(String ing : ingredients){

            String [] strs = ing.split("=");
            long v1 = Integer.parseInt(strs[1]);
            Ingredient ingredient = new Ingredient(strs[0],(int) v1,100,1000);
            coffeeMachineDaoSingleton.addIngredients(ingredient);
        }
    }

    public static void setBeverage(String beveragesString){

        String[] beverages = beveragesString.split(";");

        for(String bev : beverages){
            String[] bev1 = bev.split(":");
            Beverage beverage = new Beverage(bev1[0]);
            coffeeMachineDaoSingleton.addBeverage(beverage);

            String[] items = bev1[1].split(",");

            //System.out.println(bev+" : ");
            for( String item : items ){
                //System.out.println(item);

                String[] item1 = item.split(" ");
                long l1 = Integer.parseInt(item1[1]);

                beverage.getIngredients().put(item1[0],(int)l1);
            }
            //System.out.println();
        }

    }


    public static void main(String[] args) {
        setDAO();

        for(String str : coffeeMachineDaoSingleton.getBeverages().keySet() ){
            System.out.println(str+" : ");

            for( String str1 : coffeeMachineDaoSingleton.getBeverages().get(str).getIngredients().keySet() ){
                System.out.println(str1+" "+ coffeeMachineDaoSingleton.getBeverages().get(str).getIngredients().get(str1));
            }


        }
    }

}
