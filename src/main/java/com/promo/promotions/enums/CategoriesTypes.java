package com.promo.promotions.enums;

public class CategoriesTypes {

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

    public CategoriesTypes(Categories category,String url,boolean needsinsertintovalue){
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
}