package com.promo.promotions;

import com.promo.promotions.Methods.MyString;
import org.junit.jupiter.api.Test;

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


}
