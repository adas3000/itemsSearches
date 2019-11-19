package com.promo.promotions.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.promo.promotions.enums.SerachIn;
import com.promo.promotions.exceptions.NoSuchSearcher;
import com.promo.promotions.model.Item;

import java.util.ArrayList;
import java.util.List;

public class Searches {

    public Searches(){

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
            // searchUrl = originUrl+"&p="+nextPage;
            //  nextPage++;
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        //   }
        return searchResult;
    }

    public List<Item> SearchByString(String value, SerachIn serachIns) {


        String pathPrice = "";
        String pathName = "";
        String searchUrl = "";
        String getByXPath = "";

        List<Item> searchResult = new ArrayList<>();

        switch (serachIns) {
            case Allegro:
                pathPrice = ".//span[@class='fee8042']";
                pathName = ".//h2[@class='ebc9be2 _5087f6f']/a";
                searchUrl = "https://allegro.pl/listing?string=" + value;
                getByXPath = "//div[@class='b659611 _307719f']";
                break;
            case Amazon:
                pathPrice = ".//span[@class='a-price']";
                pathName = ".//span[@class='a-size-medium a-color-base a-text-normal']";
                searchUrl = "https://www.amazon.com/s?k=" + value;
                getByXPath = "//div[@class='sg-col-20-of-24 sg-col-28-of-32 sg-col-16-of-20 sg-col s-right-column sg-col-32-of-36 sg-col-8-of-12 sg-col-12-of-16 sg-col-24-of-28']";
                break;
            default:
                throw new NoSuchSearcher("Wrong SerachIn value");
        }

        return this.search(pathPrice,pathName,searchUrl,getByXPath);
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

                if (itemAnchor == null) continue;

                String itemPrice = spanPrice == null ? "0.0" : spanPrice.asText();

                Item item1 = new Item();
                item1.setUrl(itemAnchor.getHrefAttribute());
                item1.setTitle(itemAnchor.asText());
                item1.setPrice(Item.aStringToBDecimal(itemPrice));


                searchResult.add(item1);
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return searchResult;
    }

}
