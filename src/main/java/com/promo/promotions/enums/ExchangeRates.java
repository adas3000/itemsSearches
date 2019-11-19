package com.promo.promotions.enums;

public enum ExchangeRates {

    Dolar,Euro,Pound;
    private double value;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double toZl(double w){
        return value*w;
    }

}
