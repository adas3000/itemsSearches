package com.promo.promotions.request;

public class SearchCategoryAndValueRequest {
    public String category;
    public String value;

    public SearchCategoryAndValueRequest(String category, String value) {
        this.category = category;
        this.value = value;
    }
}
