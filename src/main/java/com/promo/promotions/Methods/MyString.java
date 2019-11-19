package com.promo.promotions.Methods;

public class MyString {

    public static String insert(String str,String what,int where){

        String result = str.substring(0,where);
        result += what;
        result += str.substring(str.length()-2);

        return result;
    }
}
