package com.promo.promotions.controller;

import com.promo.promotions.model.Item;
import com.promo.promotions.search.Searches;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/example")
public class ExampleController {


    @GetMapping("/items")
    public List<String> items(){
        return List.of("Bake","Cake","Tequila","Else");
    }

    @GetMapping("/allegrosearch")
    public List<Item> searchItems(@RequestParam String searching){
        System.out.println("SERACHING "+searching);
        return Searches.allegroSearchByString(searching);
    }

}
