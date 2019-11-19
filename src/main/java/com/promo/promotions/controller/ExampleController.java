package com.promo.promotions.controller;

import com.promo.promotions.model.Item;
import com.promo.promotions.request.SearchCategoryAndValueRequest;
import com.promo.promotions.search.Searches;
import com.promo.promotions.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/example")
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
        return searchService.searchByValueAndCategory(searchCategoryAndValueRequest);
    }

    @GetMapping("/searchfortest")
    public ResponseEntity<Object> searchTests(@RequestParam String category, @RequestParam String value) {
        return searchService.searchByValueAndCategory(new SearchCategoryAndValueRequest(category, value));
    }


}
