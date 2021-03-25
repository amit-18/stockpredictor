package com.stockamarket.amit.stockpredictor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RestController
public class BSEController {

    private WebClient webClient;

    @GetMapping("/getData")
    public void printData() {
        String endpoint = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=RELIANCE.BSE&outputsize=full&apikey=demo";
        webClient = WebClient
                .builder()
                .baseUrl(endpoint)
                .build();
        log.info(webClient.get());
    }
}
