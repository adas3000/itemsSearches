package com.promo.promotions.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.promo.promotions.model.Item;

import java.util.ArrayList;
import java.util.List;

public class Searches {

    public static List<Item> allegroSearchByString(String value) {

        List<Item> searchResult = new ArrayList<>();

        WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);

        String allegroPathPrice = ".//span[@class='fee8042']";
        String allegroPathName = ".//h2[@class='ebc9be2 _5087f6f']/a";

        String searchUrl = "https://allegro.pl/listing?string=" + value;
        String originUrl = "https://allegro.pl/listing?string=" + value;
        int nextPage = 2;

      //  while(true) {
            try {
                HtmlPage page = webClient.getPage(searchUrl);
                List<HtmlElement> items = page.getByXPath("//div[@class='b659611 _307719f']");

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
                searchUrl = originUrl+"&p="+nextPage;
                nextPage++;
            } catch (Exception e) {
                System.out.println("Error:" + e.getMessage());
            }
     //   }
        return searchResult;
    }

}
