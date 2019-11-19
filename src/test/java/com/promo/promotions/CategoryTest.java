package com.promo.promotions;

import com.promo.promotions.enums.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

    @Test
    public void ifValidThenOk(){

        Category category1 = Category.valueOf("Electronic");
        Category category2 = Category.valueOf("Cloths");
        Category category3 = Category.valueOf("Cars");
        Category category4 = Category.valueOf("Books");


        assertEquals(Category.Electronic,category1);
        assertEquals(Category.Cloths,category2);
        assertEquals(Category.Cars,category3);
        assertEquals(Category.Books,category4);
    }

    @Test
    public void ifNullThenOk(){

        String cat_1WrongStr = "electronic";

        String cat_1Str = Category.Electronic.toString();
        assertNotEquals(cat_1Str,cat_1WrongStr);

    }

}
