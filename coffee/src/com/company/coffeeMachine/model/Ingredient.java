package com.company.coffeeMachine.model;

public class Ingredient {

	// if connect with DB
    private int uID;
    private static int UID = 1;

    private String name;
    // Considering all the ingredients are liquid so measured in ml
    private int quantity;
    private int minLimit;
    private boolean indicator;
    private int maxLimit;

    public Ingredient(String name, int quantity, int minLimit, int maxLimit) {
        setuID();
        setName(name);
        
        setMinLimit(minLimit);
        setQuantity(quantity);
        setMaxLimit(maxLimit);

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(int minLimit) {
        this.minLimit = minLimit;
    }

    public int getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(int maxLimit) {
        this.maxLimit = maxLimit;
    }

}
