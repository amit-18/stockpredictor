package com.stockamarket.amit.stockpredictor.controller;

import com.google.gson.Gson;
import com.stockamarket.amit.stockpredictor.service.HistoricalData;
import com.stockamarket.amit.stockpredictor.service.Symbols;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

@Slf4j
@RestController
public class BSEController {

    RestTemplate restTemplate = new RestTemplate();
    Symbols symbols = new Symbols();
    HistoricalData historicalData = new HistoricalData();
    private HashMap<Integer,String> stockCodes;

    @GetMapping("/getData")
    public ResponseEntity printData() throws Exception {
        log.info("Recieved request at getData");
        String symbol = "INFY";
        String api = "QTET1VQSNTE48TKV";
        String stock_csv_endpoint = "https://www1.nseindia.com/live_market/dynaContent/live_analysis/most_active/allTopVolume1.json";
        String endpoint = String.format("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=%s.BSE&outputsize=full&apikey=%s",symbol,api);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36 Edg/89.0.774.63");
        headers.set("Host","www1.nseindia.com");
        headers.set("Connection","keep-alive");
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        try {
              getStockCodes();
//            Gson gson = new Gson();
//            StockData data = gson.fromJson(restTemplate.exchange(endpoint, HttpMethod.GET, entity, String.class).getBody(), new TypeToken<StockData>() {
//            }.getType());
//            historicalData.data();
            return ResponseEntity.ok().body("Ran");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e);
        }
    }

    public void getStockCodes() throws Exception {
        String line = "";
        String splitBy = ",";
        stockCodes = new HashMap<Integer,String>();
        BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Equity.csv"));
        while((line = br.readLine()) != null) {
            String[] stock = line.split(splitBy);
            log.info(stock[0]+":"+stock[2]);
        }
    }

}
