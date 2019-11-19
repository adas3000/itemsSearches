package com.promo.promotions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.promo.promotions.Methods.MyString;
import com.promo.promotions.enums.SerachIn;
import com.promo.promotions.model.Item;
import com.promo.promotions.search.Searches;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SearchesTest {

    @Test
    public void ifFoundItemsThenOk() throws JsonProcessingException {

        String searchingValue = "Iphone X";

        Searches searches = new Searches();
        List<Item> items = searches.SearchByString(searchingValue, SerachIn.Amazon);
        List<Item> items1 = searches.SearchByString(searchingValue,SerachIn.MediaExpert);
        items.forEach(i -> i.setUrl("https://www.amazon.com" + i.getUrl()));

        assertTrue(items1.size()>0);

         assertTrue(items.size() > 0);
        for (Item i : items) {
            System.out.println(i.getTitle() + " " + i.getFullPrice() + " " + i.getUrl());
        }
    }

    @Test
    public void ifFoundItemsThenOkMediaE(){

        String searchingValue = "Iphone X";

        Searches searches = new Searches();
        List<Item> items = searches.SearchByString(searchingValue,SerachIn.MediaExpert);
        items.forEach(i -> i.setUrl("https://www.mediaexpert.pl" + i.getUrl()));
        items.forEach(i->i.setFullPrice(MyString.insert(i.getFullPrice(),".",i.getFullPrice().length()-2)+"zł"));

        assertTrue(items.size()>0);
        for (Item i : items) {
            System.out.println(i.getTitle() + " " + i.getFullPrice() + " " + i.getUrl());
        }
    }


}
