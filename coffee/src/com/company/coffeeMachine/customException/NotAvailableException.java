package com.company.coffeeMachine.customException;

public class NotAvailableException extends RuntimeException {
    public NotAvailableException(String msg){
        super(msg);
    }
}
