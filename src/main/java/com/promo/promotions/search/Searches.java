package com.promo.promotions.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.promo.promotions.Methods.MyString;
import com.promo.promotions.enums.CategoriesTypes;
import com.promo.promotions.enums.ExchangeRates;
import com.promo.promotions.model.Item;
import com.promo.promotions.util.Shop;
import com.promo.promotions.util.ShopList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Searches {

    @Autowired
    private ShopList shopList;


    public Searches() {

    }


    public List<Shop> findShopsByCategory(String value, CategoriesTypes.Categories categories){

        List<Shop> serachIn = shopList.getShopList();

        return serachIn.stream().filter(s->s.hasCategory(categories)).collect(Collectors.toList());
    }


    public List<Item> findAllByShopAndCategory(String value, Shop shop, CategoriesTypes.Categories categories) {

        CategoriesTypes categoriesTypes = shop.getCategoriesTypesList().stream().filter(s -> s.getCategory().equals(categories)).findFirst().orElse(null);

        if (categoriesTypes == null) {
            return List.of();
        }
        String url = categoriesTypes.isNeedsinsertintovalue() ? categoriesTypes.getUrl().replace("value", value) : categoriesTypes.getUrl() + value;
        url = url.replace(" ", "+");
        List<Item> items = this.search(shop.getPathPrice(), shop.getPathName(), url, shop.getGetByXPathParent());
        items.forEach(item -> {
            if (shop.isNeedsOriginUrlTohref()) item.setUrl(shop.getOriginUrl() + item.getUrl());
            if(!item.getFullPrice().contains(".")) item.setFullPrice(MyString.insert(item.getFullPrice(),".",item.getFullPrice().length()-2));
            if(!item.getFullPrice().contains("zł") && !item.getFullPrice().contains("$")) item.setFullPrice(item.getFullPrice()+" zł");
            item.setShop(shop.getShopName());
        });
        return items;
    }


    private List<Item> search(String pathPrice, String pathName, String searchUrl, String getByXPath) {
        List<Item> searchResult = new ArrayList<>();

        try {
            WebClient webClient = new WebClient();
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(false);

            HtmlPage page = webClient.getPage(searchUrl);
            List<HtmlElement> items = page.getByXPath(getByXPath);

            for (HtmlElement item : items) {
                HtmlAnchor itemAnchor = item.getFirstByXPath(pathName);
                HtmlElement spanPrice = item.getFirstByXPath(pathPrice);

                if (itemAnchor == null || spanPrice == null) {
                    continue;
                }
                String itemPrice = spanPrice.asText();
                Item item1 = new Item();
                item1.setUrl(itemAnchor.getHrefAttribute());
                item1.setTitle(itemAnchor.asText());
                item1.setFullPrice(itemPrice);
                item1.setPrice(Item.aStringToBDecimal(itemPrice));

                if(itemPrice.contains("$")){
                    item1.setPriceInPlnIfPlnNotDefault(MyString.toPln(ExchangeRates.Dolar,item1.getPrice()));
                }

                searchResult.add(item1);
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return searchResult;
    }

}
