package com.promo.promotions.enums;

import static com.promo.promotions.enums.CategoriesTypes.*;

public enum Shops {

    Allegro(new CategoriesTypes[]{new CategoriesTypes(Categories.All, "https://allegro.pl/listing?string=", false),
            new CategoriesTypes(Categories.Electronic, "", false),
            new CategoriesTypes(Categories.Cloths, "", false),
            new CategoriesTypes(Categories.Books,"https://allegro.pl/kategoria/ksiazki-i-komiksy?string=",false),
            new CategoriesTypes(Categories.Cars,"",false)}, "https://allegro.pl"),
    Amazon(new CategoriesTypes[]{new CategoriesTypes(Categories.All,"https://www.amazon.com/s?k=",false),
            new CategoriesTypes(Categories.All, "", false),
            new CategoriesTypes(Categories.Electronic, "https://www.amazon.com/s?k=value&i=electronics", true),
            new CategoriesTypes(Categories.Books, "https://www.amazon.com/s?k=value&i=stripbooks", true),
    },"https://amazon.com");

    private String originUrl;
    private CategoriesTypes[] categories;

    Shops(CategoriesTypes[] categories, String originUrl) {
        this.categories = categories;
        this.originUrl = originUrl;
    }
}
