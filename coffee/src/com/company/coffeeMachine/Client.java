package com.company.coffeeMachine;

import com.company.coffeeMachine.customException.NotAvailableException;
import com.company.coffeeMachine.service.PrepareBeveragesService;
import com.company.coffeeMachine.service.TaskExecutor;
import com.company.coffeeMachine.util.ReadFileUtil;
import com.company.coffeeMachine.util.ReadJSONUtil;
import com.company.coffeeMachine.util.SaveFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader( System.in ));

        System.out.println("it is restart or refresh?");

        String input = br.readLine();
        if(input.equals("restart")){
            // file name, if it will restart then it will read from JSON file
            ReadJSONUtil.setDAO("test1.json");

        }else{
        	// It will read from previous stage
            ReadFileUtil.setDAO();
        }

        System.out.println("Welcome ...");

        //ResourceManagerSingleton coffeeMachineDaoSingleton = ResourceManagerSingleton.getInstance();
        //SaveFile.saveIntoFile();

        while(true){
            System.out.println("Please give the input as Beverage");
            String s1 = br.readLine();
            if( s1.equals( "shutdown") ){
                // save into file before shutdown
                SaveFile.saveIntoFile();
                // close the ThreadExecuter
                TaskExecutor.getCommonExecutor().shutdown();
                break;
            }

            try{
                PrepareBeveragesService.getBeverages(s1);
            }catch (NotAvailableException e){
                System.out.println(e.getMessage());
            }

        }

    }

}