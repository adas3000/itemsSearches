package com.promo.promotions;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.promo.promotions.model.Item;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class GetPriceTest {


    @Test
    public void ifWorkThenOk() {

        String searchQuery = "Iphone X";
        WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);

        String allegroPathPrice = ".//span[@class='fee8042']";
        String allegroPathName = ".//h2[@class='ebc9be2 _5087f6f']/a";
        try {
            //"https://newyork.craigslist.org/search/sss?sort=rel&query=" + URLEncoder.encode(searchQuery, "UTF-8");

            List<Item> searchResult = new ArrayList<>();

            int nextPage = 2;
            String searchUrl = "https://allegro.pl/listing?string=" + searchQuery;
            String searchUrlOrigin="https://allegro.pl/listing?string="+searchQuery;

            boolean hasNextPage = true;
            while (hasNextPage) {

                HtmlPage page = webClient.getPage(searchUrl);
                List<HtmlElement> items = page.getByXPath("//div[@class='b659611 _307719f']");
                page = webClient.getPage(searchUrl);
                items = page.getByXPath("//div[@class='b659611 _307719f']");

               // assertFalse(items.isEmpty());

                if(items.isEmpty()){
                hasNextPage = false;
                }

                for (HtmlElement item : items) {
                    //   HtmlAnchor itemAnchor = (item.getFirstByXPath(".//p[@class='result-info']/a"));
                    //   HtmlElement spanPrice = (item.getFirstByXPath(".//a/span[@class='result-price']"));
                    HtmlAnchor itemAnchor = item.getFirstByXPath(allegroPathName);
                    HtmlElement spanPrice = item.getFirstByXPath(allegroPathPrice);

                    if (itemAnchor == null) continue;

                    String itemPrice = spanPrice == null ? "0.0" : spanPrice.asText();

                    Item item1 = new Item();
                    item1.setUrl(itemAnchor.getHrefAttribute());
                    item1.setTitle(itemAnchor.asText());
                    //item1.setPrice(new BigDecimal(itemPrice.replace("$", "")));
                    //System.out.println(itemPrice);
                    item1.setPrice(Item.aStringToBDecimal(itemPrice));
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonString = objectMapper.writeValueAsString(item1);

                    System.out.println("OUTPUT: " + jsonString);
                    searchResult.add(item1);
                }
                System.out.println("Items count:" + items.size());
                searchUrl = searchUrlOrigin+"&p="+nextPage++;
            }

        } catch (Exception e) {

            System.out.println("Error:" + e.getMessage());
        }


    }

    @Test
    public void ifBigDecimalOkThenCool() {


        String value1 = "2 995,00 zł";
        String value2 = "2 999,00 zł";
        String value3 = "3 299,00 zł";

        assertEquals(new BigDecimal("2995.00"), Item.aStringToBDecimal(value1));
        assertEquals(new BigDecimal("2999.00"), Item.aStringToBDecimal(value2));
        assertEquals(new BigDecimal("3299.00"), Item.aStringToBDecimal(value3));
    }


}
