package com.promo.promotions;

import com.promo.promotions.enums.Category;
import com.promo.promotions.model.Item;
import com.promo.promotions.search.Searches;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SearchThreadTest {


    @Test
    public void ifWaitThenOk() throws InterruptedException {
        Searches searches = new Searches();

        List<Item> result = new ArrayList<>();
        String value = "Iphone 3";
        List<Thread> myThreads = new ArrayList<>();

        for (Category.SerachIn serachIn : Category.Electronic.getSerachIn()) {
            Thread thread = new Thread(() -> {
                result.addAll(searches.SearchByString(value, serachIn));
                System.out.println("Thread:" + serachIn.toString() + " finished");
            });
            myThreads.add(thread);
            thread.start();
        }
        for(Thread t : myThreads){
            while(t.isAlive()) continue;
        }
        System.out.println("All threads finished theirs jobs");
    }


}
