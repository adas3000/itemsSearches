package com.promo.promotions.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.promo.promotions.enums.CategoriesTypes.*;

public enum Shops {

    Allegro(new CategoriesTypes[]{new CategoriesTypes(Categories.All, "https://allegro.pl/listing?string=", false),
            new CategoriesTypes(Categories.Electronic, "https://allegro.pl/kategoria/elektronika?string=", false),
            new CategoriesTypes(Categories.Cloths, "https://allegro.pl/kategoria/moda?string=", false),
            new CategoriesTypes(Categories.Books, "https://allegro.pl/kategoria/ksiazki-i-komiksy?string=", false),
            new CategoriesTypes(Categories.Cars, "https://allegro.pl/kategoria/motoryzacja?string=", false)},
            "https://allegro.pl",false,".//span[@class='fee8042']",".//h2[@class='ebc9be2 _5087f6f']/a","//div[@class='b659611 _307719f']"),
    Amazon(new CategoriesTypes[]{new CategoriesTypes(Categories.All, "https://www.amazon.com/s?k=", false),
            new CategoriesTypes(Categories.Electronic, "https://www.amazon.com/s?k=" + replacementvalue + "&i=electronics", true),
            new CategoriesTypes(Categories.Books, "https://www.amazon.com/s?k=" + replacementvalue + "&i=stripbooks", true),
    }, "https://amazon.com",true,".//span[@class='a-price']/span",".//a[@class='a-link-normal a-text-normal']","//div[@class='sg-col-inner']");

    private String originUrl;
    private CategoriesTypes[] categories;
    private List<CategoriesTypes> categoriesTypesList;
    private String pathPrice;
    private String pathName;
    private String getByXPathParent;
    private boolean needsOriginUrlTohref;

    public boolean isNeedsOriginUrlTohref() {
        return needsOriginUrlTohref;
    }

    public void setNeedsOriginUrlTohref(boolean needsOriginUrlTohref) {
        this.needsOriginUrlTohref = needsOriginUrlTohref;
    }

    Shops(CategoriesTypes[] categories, String originUrl, boolean needsOriginUrlTohref, String pathPrice, String pathName, String getByXPathParent) {
        this.originUrl = originUrl;
        this.categories = categories;
        this.categoriesTypesList = new ArrayList<>(Arrays.asList(categories));
        this.pathPrice = pathPrice;
        this.pathName = pathName;
        this.needsOriginUrlTohref = needsOriginUrlTohref;
        this.getByXPathParent = getByXPathParent;
    }

    Shops(CategoriesTypes[] categories, String originUrl) {
        this.categories = categories;
        this.originUrl = originUrl;
        this.categoriesTypesList = new ArrayList<>(Arrays.asList(categories));
    }

    public List<CategoriesTypes> getCategoriesTypesList() {
        return categoriesTypesList;
    }

    public boolean hasCategory(Categories categories){
        return categoriesTypesList.stream().anyMatch(c -> c.getCategory().equals(categories));
    }

    public void setCategoriesTypesList(List<CategoriesTypes> categoriesTypesList) {
        this.categoriesTypesList = categoriesTypesList;
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
}
