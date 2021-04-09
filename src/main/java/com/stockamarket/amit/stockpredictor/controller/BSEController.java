package com.stockamarket.amit.stockpredictor.controller;

import com.google.gson.Gson;
import com.stockamarket.amit.stockpredictor.service.HistoricalData;
import com.stockamarket.amit.stockpredictor.service.StockFinder;
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
    StockFinder stockFinder = new StockFinder();

    @GetMapping("/getData")
    public ResponseEntity printData() throws Exception {
        log.info("Recieved request at getData");
        try {

//            Gson gson = new Gson();
//            StockData data = gson.fromJson(restTemplate.exchange(endpoint, HttpMethod.GET, entity, String.class).getBody(), new TypeToken<StockData>() {
//            }.getType());
//            historicalData.data();
//            stockFinder.createBarSeries();
            stockFinder.runBarSeries();
            return ResponseEntity.ok().body("RAN");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e);
        }
    }


}
