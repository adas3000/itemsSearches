package com.promo.promotions;

import com.promo.promotions.Methods.MyString;
import com.promo.promotions.enums.ExchangeRates;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class MyStringTest {

    @Test
    public void ifEqualsThenOk(){

        String price1 = "49990";
        String price2 = "4990";
        String price3 = "499";
        assertEquals("499.90", MyString.insert(price1,".",price1.length()-2));
        assertEquals("49.90", MyString.insert(price2,".",price2.length()-2));
        assertEquals("4.99", MyString.insert(price3,".",price3.length()-2));
    }

    @Test
    public void ifEqualsThenOk1(){

        ExchangeRates.Dolar.setValue(3.75);
        String value = MyString.toPln(ExchangeRates.Dolar,new BigDecimal("123.123"));
        assertEquals(new BigDecimal(123.123*ExchangeRates.Dolar.getValue()).toString(),value);
    }

    @Test
    public void ifEqualsThenOk2(){
        String fullPrice = "10700";

        assertEquals("107.00",MyString.insert(fullPrice,".",fullPrice.length()-2));
    }

}
