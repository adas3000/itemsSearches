package com.promo.promotions.Methods;

import com.promo.promotions.enums.ExchangeRates;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MyString {

    public static String insert(String str,String what,int where){

        String result = str.substring(0,where);
        result += what;
        result += str.substring(str.length()-2);

        return result;
    }

    public static String toPln(ExchangeRates from, BigDecimal value){
        BigDecimal pln = new BigDecimal("0");
        pln = pln.add(new BigDecimal(value.doubleValue()*from.getValue()));
        return pln.setScale(2, RoundingMode.CEILING).toString()+" zł";
    }

}
