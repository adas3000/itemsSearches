package com.promo.promotions.exceptions;

public class NoSuchSearcher extends RuntimeException {

    public NoSuchSearcher(){
        super();
    }
    public NoSuchSearcher(String message){
        super(message);
    }

}
