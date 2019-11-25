package com.promo.promotions.enums;

import java.util.ArrayList;
import java.util.List;

public class CategoriesTypes {

    public static final String replacementvalue = "value";

    public enum Categories {
        All,
        Electronic,
        Cloths,
        Cars,
        Books;
    }

    private Categories category;
    private String url;
    private boolean needsinsertintovalue;


    public CategoriesTypes(Categories category, String url, boolean needsinsertintovalue) {
        this.category = category;
        this.url = url;
        this.needsinsertintovalue = needsinsertintovalue;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isNeedsinsertintovalue() {
        return needsinsertintovalue;
    }

    public void setNeedsinsertintovalue(boolean needsinsertintovalue) {
        this.needsinsertintovalue = needsinsertintovalue;
    }

    public static List<String> getAllToStr() {

        List<String> arr = new ArrayList<>();

        for (Categories c : Categories.values()) {
            arr.add(c.toString());
        }
        return arr;
    }

}