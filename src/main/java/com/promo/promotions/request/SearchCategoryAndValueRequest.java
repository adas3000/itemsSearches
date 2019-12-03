package com.promo.promotions.request;

public class SearchCategoryAndValueRequest {

    public String category;
    public String value;
    public String limit;

    public SearchCategoryAndValueRequest(String category, String value) {
        this.category = category;
        this.value = value;
    }

    public SearchCategoryAndValueRequest(String category, String value, String limit) {
        this.category = category;
        this.value = value;
        this.limit = limit;
    }
}
