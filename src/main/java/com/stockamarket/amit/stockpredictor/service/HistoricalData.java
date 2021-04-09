package com.stockamarket.amit.stockpredictor.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stockamarket.amit.stockpredictor.Entity.StockData;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.stockamarket.amit.stockpredictor.constants.Constants.*;

@Slf4j
@Component
public class HistoricalData {
    Symbols symbols = new Symbols();
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity;
    public void createConnection() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36 Edg/89.0.774.63");
        headers.set("Host","www.alphavantage.co");
        headers.set("Connection","keep-alive");
        entity = new HttpEntity<String>(headers);
    }

    @Scheduled(cron = "0 36 23 * * MON-FRI")
    public void data() throws Exception {
        List<String> stocks = symbols.getStockSymbols();
        log.info("Getting Historical Data for"+stocks.size()+" symbols");
        HashMap<String ,String> stocksSymbolsWithId = getStockCodesWithId();
        stocks.forEach((symbol) -> {
                    try {
                        String id = stocksSymbolsWithId.get(symbol);
                        getHistoricalData(symbol, id);
                        Thread.sleep(24000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        log.info("Historical data Fetch Complete");
    }


    public void getHistoricalData(String symbol, String id) throws IOException {
        String histEndpt = String.format(HISTORICAL_DATA_URL,symbol,API_KEY);
        String histEndptWithId = String.format(HISTORICAL_DATA_URL_WITH_ID,id,API_KEY);
//        Gson gson = new Gson();
//        StockData data = gson.fromJson(restTemplate.exchange(histEndpt, HttpMethod.GET, entity, String.class).getBody(), new TypeToken<StockData>() {
//            }.getType());
        String fileName = String.format("src/main/data/%s.json",symbol);
        FileWriter file = new FileWriter(fileName,false);
        BufferedWriter writer = new BufferedWriter(file, 51200);
        String response = Objects.requireNonNull(restTemplate.exchange(histEndptWithId, HttpMethod.GET, entity, String.class).getBody());
        if(response.length()>153){
            writer.write(response);
            log.info("Getting Historical Data for symbol: " + symbol);
        }
        else{
            writer.write(Objects.requireNonNull(restTemplate.exchange(histEndpt, HttpMethod.GET, entity, String.class).getBody()));
            log.info("Getting Historical Data for symbol: " + symbol + ":" + id);
        }
        writer.close();
    }

    public HashMap<String, String> getStockCodesWithId() throws Exception {
        String line = "";
        String splitBy = ",";
        HashMap<String,String> stockCodes = new HashMap<String,String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Equity.csv"));
            while ((line = br.readLine()) != null) {
                String[] stock = line.split(splitBy);
                stockCodes.put(stock[2], stock[0]);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return stockCodes;
    }

}
