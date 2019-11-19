package com.promo.promotions.comparators;

import com.promo.promotions.model.Item;

import java.util.Comparator;

public class SortByPrice implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
        return o1.getPrice().compareTo(o2.getPrice());
    }

}
