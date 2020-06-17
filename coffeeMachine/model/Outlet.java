package com.company.LLD.coffeeMachine.model;

public class Outlet {

    private int uID;
    private static int UID = 1;
    private Beverage beverage;

    public Outlet() {
        setuID();
    }

    private void setuID() {
        // todo: when we will use DB (Hibernate), we can use @AutoGenerator
        //  or other technique as per the requirement but here we are using this technique
        this.uID = UID;
        UID++;
    }

    public int getuID() {
        return uID;
    }

    //todo: select the beverage
    public String prepareBeverage(Beverage beverage){
        //todo:throw custom Exception if the Beverage is null
        // can be add outlet id also
        return beverage.getBeverage();
    }

}
