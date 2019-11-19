package com.promo.promotions.model;

import java.math.BigDecimal;

public class Item {
    private String title;
    private BigDecimal price;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static BigDecimal aStringToBDecimal(String value) {

        value = value.replace(" ", "");

        if(value.contains("zł")) value = value.replace("zł", "");
        else if(value.contains("$")) value = value.replace("$","");

        value = value.replace(",", ".");
        return new BigDecimal(value);
    }


}
