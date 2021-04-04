package com.stockamarket.amit.stockpredictor.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stockamarket.amit.stockpredictor.Entity.StockData;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.stockamarket.amit.stockpredictor.constants.Constants.*;

public class HistoricalData {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity;
    public void createConnection() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36 Edg/89.0.774.63");
        headers.set("Host","www1.nseindia.com");
        headers.set("Connection","keep-alive");
        entity = new HttpEntity<String>(headers);
    }

    public StockData getHistoricalData(String symbol){
        String histEndpt = String.format(HISTORICAL_DATA_URL,symbol,API_KEY);
        Gson gson = new Gson();
        StockData data = gson.fromJson(restTemplate.exchange(histEndpt, HttpMethod.GET, entity, String.class).getBody(), new TypeToken<StockData>() {
            }.getType());
        return data;
    }
}
