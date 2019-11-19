package com.promo.promotions.service;

import com.promo.promotions.enums.Category;
import com.promo.promotions.enums.Category.SerachIn;
import com.promo.promotions.model.Item;
import com.promo.promotions.request.SearchCategoryAndValueRequest;
import com.promo.promotions.search.Searches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@Service
public class SearchService {

    @Autowired
    private Searches searches;


    public ResponseEntity<Object> searchByValueAndCategory(SearchCategoryAndValueRequest valueRequest)  {

        Category category;
        try {
            category = Category.valueOf(valueRequest.category);
        } catch (Exception e) {
            return new ResponseEntity<>("No such category", HttpStatus.NOT_FOUND);
        }
        String value = valueRequest.value;
        List<Item> result = new ArrayList<>();

        try {
            List<Thread> currentThreads = new ArrayList<>();
            for (SerachIn serachIn : category.getSerachIn()) {
                Thread thread = new Thread(() -> {
                    synchronized (result)
                    {
                        result.addAll(searches.SearchByString(value, serachIn));
                    }
                    System.out.println("Thread:" + serachIn.toString() + " finished");
                });
                currentThreads.add(thread);
                thread.start();
            }
            for (Thread t : currentThreads) {
                while (t.isAlive()) TimeUnit.SECONDS.sleep(1);
            }

        return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch(Exception e){
            e.fillInStackTrace();
            System.out.println("Exception:"+e.getMessage());
            return new ResponseEntity<>("error",HttpStatus.BAD_REQUEST);
        }
    }


}
