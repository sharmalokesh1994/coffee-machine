package com.company.coffeeMachine.util;

import com.company.coffeeMachine.dao.ResourceManagerSingleton;
import com.company.coffeeMachine.model.Beverage;
import com.company.coffeeMachine.model.Ingredient;
import com.company.coffeeMachine.service.TaskExecutor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// for reading from the JSON file and preparing resources
public class ReadJSONUtil {

    private static ResourceManagerSingleton coffeeMachineDaoSingleton = ResourceManagerSingleton.getInstance();
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

        long v1 = (Long) outlets.get("count_n");
        int outlet = (int) v1;
        coffeeMachineDaoSingleton.setOutlets(outlet);
        // Start thread Executor
        TaskExecutor.setCommonExecutor(outlet);

        //System.out.println(outlets.get("count_n").getClass());

    }

    public static void setIngredients(JSONObject ingredients){
        // considering max limit is 1000 for every ingredients
        for(Object ing : ingredients.keySet()){

            long v1 = (Long) ingredients.get(ing);
            Ingredient ingredient = new Ingredient(ing.toString(),(int) v1,400,1000);
            coffeeMachineDaoSingleton.addIngredients(ingredient);
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

                beverage.getIngredients().put(item.toString(),(int)l1);
            }
            //System.out.println();
        }

    }


    public static void main(String[] args) {
        setDAO("D:\\Desktop\\AEM_Training\\aem_projects\\java_fx\\java\\GeeksForGeeksTest\\src\\test1.json");

        for(String str : coffeeMachineDaoSingleton.getBeverages().keySet() ){
            System.out.println(str+" : ");

            for( String str1 : coffeeMachineDaoSingleton.getBeverages().get(str).getIngredients().keySet() ){
                System.out.println(str1+" "+ coffeeMachineDaoSingleton.getBeverages().get(str).getIngredients().get(str1));
            }


        }
    }

}
