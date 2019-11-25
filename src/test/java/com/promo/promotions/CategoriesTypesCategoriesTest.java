package com.promo.promotions;

import com.promo.promotions.enums.CategoriesTypes;
import org.junit.jupiter.api.Test;

public class CategoriesTypesCategoriesTest {

    @Test
    public void categoriestostr() {

        String[] tab = new String[CategoriesTypes.Categories.values().length];
        tab[0] = CategoriesTypes.Categories.All.toString();

        System.out.println(tab[0]);

    }


}
