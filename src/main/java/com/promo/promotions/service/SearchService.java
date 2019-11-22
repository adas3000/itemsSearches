package com.promo.promotions.service;

import com.promo.promotions.comparators.SortByPrice;
import com.promo.promotions.enums.CategoriesTypes;
import com.promo.promotions.enums.Shops;
import com.promo.promotions.model.Item;
import com.promo.promotions.request.SearchCategoryAndValueRequest;
import com.promo.promotions.search.Searches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class SearchService {

    @Autowired
    private Searches searches;


    public ResponseEntity<Object> searchByValueAndCategoryDiffMeth(SearchCategoryAndValueRequest valueRequest) {
        CategoriesTypes.Categories category;
        try {
            category = CategoriesTypes.Categories.valueOf(valueRequest.category);
        } catch (Exception e) {
            return new ResponseEntity<>("No such category", HttpStatus.NOT_FOUND);
        }
        String value = valueRequest.value;

        List<Shops> searchIn = searches.findShopsByCategory(value, category);
        if (searchIn == null || searchIn.size() == 0) {
            return new ResponseEntity<>("no_shops_founded", HttpStatus.NOT_FOUND);
        }

        List<Item> items = new ArrayList<>();
        try {
            List<Thread> currentThreads = new ArrayList<>();

            for (Shops shop : searchIn) {
                Thread thread = new Thread(() -> {
                    synchronized (items) {
                        items.addAll(searches.findAllByShopAndCategory(value, shop, category));
                    }
                    System.out.print("Thread:" + shop.toString() + " finished\n");
                });
                currentThreads.add(thread);
                thread.start();
            }
            for (Thread t : currentThreads) {
                while (t.isAlive()) TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            System.out.print("Exception:" + e.getMessage());
            return new ResponseEntity<>("Error_in_searchByValueAndCategoryDiffMeth", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }


}
