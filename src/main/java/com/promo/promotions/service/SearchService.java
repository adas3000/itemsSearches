package com.promo.promotions.service;

import com.promo.promotions.enums.Category;
import com.promo.promotions.enums.SerachIn;
import com.promo.promotions.model.Item;
import com.promo.promotions.request.SearchCategoryAndValueRequest;
import com.promo.promotions.search.Searches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SearchService {

    @Autowired
    private Searches searches;


    public ResponseEntity<Object> searchByValueAndCategory(SearchCategoryAndValueRequest valueRequest) {

        Category category;
        try {
            category = Category.valueOf(valueRequest.category);
        }
        catch(Exception e){
            return new ResponseEntity<>("No such category",HttpStatus.NOT_FOUND);
        }
        String value = valueRequest.value;
        List<Item> result = new ArrayList<>();

        switch (category) {
            case Electronic:
                List<Item> amazonlist = searches.SearchByString(value, SerachIn.Amazon);
                amazonlist.forEach(i -> i.setUrl("https://www.amazon.com" + i.getUrl()));
                result.addAll(amazonlist);
                new Thread(()->{
                    result.addAll(searches.SearchByString(value, SerachIn.Allegro));
                }).start();

                break;
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
