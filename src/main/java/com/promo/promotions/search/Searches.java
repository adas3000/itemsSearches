package com.promo.promotions.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.promo.promotions.enums.CategoriesTypes;
import com.promo.promotions.enums.Shops;
import com.promo.promotions.model.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Searches {

    public Searches() {

    }

    public static List<Item> allegroSearchByString(String value) {

        List<Item> searchResult = new ArrayList<>();

        WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);

        String allegroPathPrice = ".//span[@class='fee8042']";
        String allegroPathName = ".//h2[@class='ebc9be2 _5087f6f']/a";

        String searchUrl = "https://allegro.pl/listing?string=" + value;
        // String originUrl = "https://allegro.pl/listing?string=" + value;
        //  int nextPage = 2;

        //  while(true) {
        try {
            HtmlPage page = webClient.getPage(searchUrl);
            List<HtmlElement> items = page.getByXPath(".//div[@class='_65c539b']");

            System.out.println("Items count:" + items.size());

            if (items.isEmpty()) {
                return searchResult;
            }


            for (HtmlElement item : items) {
                HtmlAnchor itemAnchor = item.getFirstByXPath(allegroPathName);
                HtmlElement spanPrice = item.getFirstByXPath(allegroPathPrice);

                if (itemAnchor == null) continue;

                String itemPrice = spanPrice == null ? "0.0" : spanPrice.asText();

                Item item1 = new Item();
                item1.setUrl(itemAnchor.getHrefAttribute());
                item1.setTitle(itemAnchor.asText());
                item1.setPrice(Item.aStringToBDecimal(itemPrice));

                ObjectMapper objectMapper = new ObjectMapper();
                String jsonString = objectMapper.writeValueAsString(item1);

                // System.out.println("OUTPUT: " + jsonString);
                searchResult.add(item1);
            }
            // System.out.println("Items count:" + items.size());
            // searchUrl = originUrl+"&p="+nextPage;
            //  nextPage++;
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        //   }
        return searchResult;
    }

    public List<Shops> findShopsByCategory(String value, CategoriesTypes.Categories categories) {

        List<Shops> searchIn = new ArrayList<>(Arrays.asList(Shops.values()));

        return searchIn.stream().filter(s -> s.hasCategory(categories)).collect(Collectors.toList());
    }

    public List<Item> findAllByShopAndCategory(String value, Shops shop, CategoriesTypes.Categories categories) {

        CategoriesTypes categoriesTypes = shop.getCategoriesTypesList().stream().filter(s -> s.getCategory().equals(categories)).findFirst().orElse(null);

        if (categoriesTypes == null) {
            return List.of();
        }
        String url = categoriesTypes.isNeedsinsertintovalue() ? categoriesTypes.getUrl().replace("value", value) : categoriesTypes.getUrl() + value;
        url = url.replace(" ", "+");
        List<Item> items = this.search(shop.getPathPrice(), shop.getPathName(), url, shop.getGetByXPathParent());
        items.forEach(item -> {
            if (shop.isNeedsOriginUrlTohref()) item.setUrl(shop.getOriginUrl() + item.getUrl());
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
                searchResult.add(item1);
                System.out.println(item1);
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return searchResult;
    }

}
