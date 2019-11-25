package com.promo.promotions.controller;

import com.promo.promotions.enums.CategoriesTypes;
import com.promo.promotions.model.Item;
import com.promo.promotions.request.SearchCategoryAndValueRequest;
import com.promo.promotions.search.Searches;
import com.promo.promotions.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/example")
@CrossOrigin
public class ExampleController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/items")
    public List<String> items() {
        return List.of("Bake", "Cake", "Tequila", "Else");
    }

    @GetMapping("/allegrosearch")
    public List<Item> searchItems(@RequestParam String searching) {
        System.out.println("SERACHING " + searching);
        return Searches.allegroSearchByString(searching);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> search(@RequestBody @NonNull @Valid SearchCategoryAndValueRequest searchCategoryAndValueRequest) {
        System.out.printf("PARAMS:category:%s,value:%s",searchCategoryAndValueRequest.category,searchCategoryAndValueRequest.value);
        return searchService.searchByValueAndCategoryDiffMeth(searchCategoryAndValueRequest);
    }

    @GetMapping("/searchfortest")
    public ResponseEntity<Object> searchTests(@RequestParam String category, @RequestParam String value) {
        return searchService.searchByValueAndCategoryDiffMeth(new SearchCategoryAndValueRequest(category, value));
    }

    @GetMapping("/categories")
    public ResponseEntity<Object> categories(){
        return new ResponseEntity<>(CategoriesTypes.getAllToStr(), HttpStatus.OK);
    }

}
