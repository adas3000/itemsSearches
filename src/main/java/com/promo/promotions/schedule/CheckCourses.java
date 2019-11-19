package com.promo.promotions.schedule;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.promo.promotions.enums.ExchangeRates;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class CheckCourses {

    public static final String USD_COURSE = "http://api.nbp.pl/api/exchangerates/rates/A/USD/today/";
    public static final String EUR_COURSE = "http://api.nbp.pl/api/exchangerates/rates/A/EUR/today/";
    public static final String GBP_COURSE = "http://api.nbp.pl/api/exchangerates/rates/A/GBP/today/";


    @Scheduled(fixedRate = 1000)
    public void checkDollarCourse() {
        try {
            ExchangeRates.Dolar.setValue(getCourse(USD_COURSE));
            ExchangeRates.Euro.setValue(getCourse(EUR_COURSE));
            ExchangeRates.Pound.setValue(getCourse(GBP_COURSE));
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }
    }

    @Scheduled(fixedRate = 5000)
    public void printCurrentCourses(){
        System.out.printf("Dolar:%s,Euro:%s,GBP:%s\n",ExchangeRates.Dolar.getValue(),ExchangeRates.Euro.getValue(),
                ExchangeRates.Pound.getValue());
    }

    private double getCourse(String link) throws IOException, ParserConfigurationException, SAXException {
        URL url = new URL(link);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();

        JsonElement element = JsonParser.parseString(content.toString());
        JsonArray jsonArray = element.getAsJsonObject().getAsJsonArray("rates");

        return jsonArray.get(0).getAsJsonObject().get("mid").getAsDouble();
    }


}
