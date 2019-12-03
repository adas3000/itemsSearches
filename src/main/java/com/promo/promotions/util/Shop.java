package com.promo.promotions.util;

import com.promo.promotions.enums.CategoriesTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Shop {


    private Long id;

    private String shopName;
    private String originUrl;
    private CategoriesTypes[] categories;
    private List<CategoriesTypes> categoriesTypesList;
    private String pathPrice;
    private String pathName;
    private String getByXPathParent;
    private boolean needsOriginUrlTohref;


    public Shop(String shopName,String originUrl, CategoriesTypes[] categories, String pathPrice, String pathName,
                String getByXPathParent, boolean needsOriginUrlTohref) {
        this.shopName = shopName;
        this.originUrl = originUrl;
        this.categories = categories;
        this.categoriesTypesList = new ArrayList<>(Arrays.asList(categories));
        this.pathPrice = pathPrice;
        this.pathName = pathName;
        this.getByXPathParent = getByXPathParent;
        this.needsOriginUrlTohref = needsOriginUrlTohref;
    }

    public boolean hasCategory(CategoriesTypes.Categories categories) {
        return categoriesTypesList.stream().anyMatch(c -> c.getCategory().equals(categories));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public CategoriesTypes[] getCategories() {
        return categories;
    }

    public void setCategories(CategoriesTypes[] categories) {
        this.categories = categories;
    }

    public List<CategoriesTypes> getCategoriesTypesList() {
        return categoriesTypesList;
    }

    public void setCategoriesTypesList(List<CategoriesTypes> categoriesTypesList) {
        this.categoriesTypesList = categoriesTypesList;
    }

    public String getPathPrice() {
        return pathPrice;
    }

    public void setPathPrice(String pathPrice) {
        this.pathPrice = pathPrice;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getGetByXPathParent() {
        return getByXPathParent;
    }

    public void setGetByXPathParent(String getByXPathParent) {
        this.getByXPathParent = getByXPathParent;
    }

    public boolean isNeedsOriginUrlTohref() {
        return needsOriginUrlTohref;
    }

    public void setNeedsOriginUrlTohref(boolean needsOriginUrlTohref) {
        this.needsOriginUrlTohref = needsOriginUrlTohref;
    }
}
