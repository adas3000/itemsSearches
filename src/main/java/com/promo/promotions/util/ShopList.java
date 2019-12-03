package com.promo.promotions.util;

import com.promo.promotions.enums.CategoriesTypes;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.promo.promotions.enums.CategoriesTypes.replacementvalue;

@Component
public class ShopList {

    private List<Shop> shopList = new ArrayList<>();

    public ShopList() {

        shopList.add(new Shop("Allegro","https://allegro.pl", new CategoriesTypes[]{new CategoriesTypes(CategoriesTypes.Categories.All, "https://allegro.pl/listing?string=", false),
                new CategoriesTypes(CategoriesTypes.Categories.Electronic, "https://allegro.pl/kategoria/elektronika?string=", false),
                new CategoriesTypes(CategoriesTypes.Categories.Cloths, "https://allegro.pl/kategoria/moda?string=", false),
                new CategoriesTypes(CategoriesTypes.Categories.Books, "https://allegro.pl/kategoria/ksiazki-i-komiksy?string=", false),
                new CategoriesTypes(CategoriesTypes.Categories.Cars, "https://allegro.pl/kategoria/motoryzacja?string=", false)},".//span[@class='fee8042']",
                ".//h2[@class='ebc9be2 _5087f6f']/a","//div[@class='b659611 _307719f']",false));

        shopList.add(new Shop("Amazon","https://amazon.com",new CategoriesTypes[]{new CategoriesTypes(CategoriesTypes.Categories.All, "https://www.amazon.com/s?k=", false),
                new CategoriesTypes(CategoriesTypes.Categories.Electronic, "https://www.amazon.com/s?k=" + replacementvalue + "&i=electronics", true),
                new CategoriesTypes(CategoriesTypes.Categories.Books, "https://www.amazon.com/s?k=" + replacementvalue + "&i=stripbooks", true),
        },".//span[@class='a-price']/span", ".//a[@class='a-link-normal a-text-normal']","//div[@class='sg-col-inner']",true));

    }

    public List<Shop> getShopList() {
        return shopList;
    }


}
