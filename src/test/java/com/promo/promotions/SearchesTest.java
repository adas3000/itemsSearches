package com.promo.promotions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.promo.promotions.enums.SerachIn;
import com.promo.promotions.model.Item;
import com.promo.promotions.search.Searches;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SearchesTest {

    @Test
    public void ifFoundItemsThenOk() throws JsonProcessingException {

        Searches searches = new Searches();
        List<Item> items = searches.SearchByString("Iphone X", SerachIn.Amazon);
        items.forEach(i -> i.setUrl("https://www.amazon.com" + i.getUrl()));


        assertTrue(items.size() > 0);
        for (Item i : items) {
            System.out.println(i.getTitle() + " " + i.getFullPrice() + " " + i.getUrl());
        }

    }


}
