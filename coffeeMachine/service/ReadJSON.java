package com.company.LLD.coffeeMachine.service;



import com.company.LLD.coffeeMachine.dao.CoffeeMachineDaoSingleton;
import com.company.LLD.coffeeMachine.model.Beverage;
import com.company.LLD.coffeeMachine.model.BeverageIngredient;
import com.company.LLD.coffeeMachine.model.Ingredient;
import com.company.LLD.coffeeMachine.model.Outlet;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadJSON {

    private static CoffeeMachineDaoSingleton coffeeMachineDaoSingleton = CoffeeMachineDaoSingleton.getInstance();
    public static void setDAO(String fileName){
        BufferedReader br = null;
        StringBuffer json =new StringBuffer();
        try{
            br = new BufferedReader(new FileReader(fileName));
            String line = "";
            while ((line = br.readLine()) != null) {
                json.append(line+"\n");
            }
        }catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }

        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(json.toString());

            JSONObject machine =((JSONObject)((JSONObject)obj).get("machine"));

            JSONObject outlets = (JSONObject) machine.get("outlets");

            //System.out.println(outlets);

            setOutlet(outlets);
            setIngredients((JSONObject) machine.get("total_items_quantity"));
            setBeverage((JSONObject) machine.get("beverages"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void setOutlet(JSONObject outlets){

        //System.out.println(outlets.get("count_n").getClass());

        long v1 = (Long) outlets.get("count_n");
        int outletNumber = (int) v1;
        TaskExecutor.setCommonExecutor(outletNumber);
        for(int i=0;i<outletNumber;i++){
            coffeeMachineDaoSingleton.addOutlets(new Outlet());
        }
    }

    public static void setIngredients(JSONObject ingredients){

        for(Object ing : ingredients.keySet()){

            long v1 = (Long) ingredients.get(ing);

            coffeeMachineDaoSingleton.addIngredients(new Ingredient(ing.toString(),(int) v1,100));
        }
    }

    public static void setBeverage(JSONObject beverages){

        for(Object bev : beverages.keySet()){
            Beverage beverage = new Beverage(bev.toString());
            coffeeMachineDaoSingleton.addBeverage(beverage);
            JSONObject items = (JSONObject) beverages.get(bev);

            //System.out.println(bev+" : ");
            for( Object item : items.keySet() ){
                //System.out.println(item);
                long l1 = (Long)items.get(item);


                BeverageIngredient beverageIngredient = new BeverageIngredient(item.toString(),(int)l1);
                beverage.getIngredients().add(beverageIngredient);
            }
            //System.out.println();
        }
    }


    public static void main(String[] args) {
        setDAO("D:\\Desktop\\AEM_Training\\aem_projects\\java_fx\\java\\GeeksForGeeksTest\\src\\test1.json");
        System.out.println(coffeeMachineDaoSingleton.getOutlets().size());

        String s1 = coffeeMachineDaoSingleton.getOutlets().get(1).prepareBeverage(coffeeMachineDaoSingleton.getBeverages().get("hot_tea"));

        System.out.println(s1);
    }

}
