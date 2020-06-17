package com.company.coffeeMachine.util;

import com.company.coffeeMachine.dao.ResourceManagerSingleton;

import java.io.FileWriter;

// this class is used for saving (the current stage) into the file
public class SaveFile {

    private static ResourceManagerSingleton resourceManagerSingleton = ResourceManagerSingleton.getInstance();

    public static void saveIntoFile(){

        try{
            FileWriter fw=new FileWriter("testout.txt");
            StringBuffer sb = new StringBuffer();
            sb.append("outlets="+resourceManagerSingleton.getOutlets()+"\n");

            int count =0;
            for( String ing : resourceManagerSingleton.getIngredients().keySet() ){
                count++;
                if(count==resourceManagerSingleton.getIngredients().size()){
                    sb.append(ing+"="+resourceManagerSingleton.getIngredients().get(ing).getQuantity());
                }else{
                    sb.append(ing+"="+resourceManagerSingleton.getIngredients().get(ing).getQuantity()+",");
                }

            }
            sb.append("\n");

            count = 0;
            for(String str : resourceManagerSingleton.getBeverages().keySet() ){
                count++;
                sb.append(str+":");

                int count1 = 0;
                for( String str1 : resourceManagerSingleton.getBeverages().get(str).getIngredients().keySet() ){
                    count1++;
                    sb.append(str1+" "+ resourceManagerSingleton.getBeverages().get(str).getIngredients().get(str1));
                    if( count1< resourceManagerSingleton.getBeverages().get(str).getIngredients().size()){
                        sb.append(",");
                    }
                }
                if(count<resourceManagerSingleton.getBeverages().size()){
                    sb.append(";");
                }


            }

            fw.write(sb.toString());

            fw.close();
        }catch(Exception e){
            System.out.println(e);
        }
        //System.out.println("Success...");
    }

    public static void main(String[] args) {
        saveIntoFile();
    }

}
