package com.company.LLD.coffeeMachine.model;

public class Ingredient {

    private int uID;
    private static int UID = 1;

    private String name;
    // Considering all the ingredients are liquid so measured in ml
    private int quantity;
    // for indicating (min limit)
    private int minLimit;
    private boolean indicator;

    public Ingredient(String name, int quantity, int minLimit) {
        setuID();
        setName(name);
        this.quantity = quantity;
        // set minLimit before setting the quantity
        setMinLimit(minLimit);
        setQuantity(quantity);

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
        this.quantity = this.quantity + quantity;

        // check or update the indicator
        if( this.quantity>minLimit && indicator){
            //System.out.println("off the indicator");
            indicator = false;
        }else{
            indicator = true;
        }
    }

    public int getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(int minLimit) {
        this.minLimit = minLimit;
    }

    public boolean getIngredientForBeverage(int quantity){
        if(getQuantity()>=quantity){
            synchronized (Ingredient.class){
                if(getQuantity()>=quantity){
                    setQuantity(-quantity);
                    return true;
                }
            }
        }
        return false;

    }
}
