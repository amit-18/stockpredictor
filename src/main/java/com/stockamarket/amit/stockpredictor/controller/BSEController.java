package com.stockamarket.amit.stockpredictor.controller;

import com.stockamarket.amit.stockpredictor.Entity.StockData;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class BSEController {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/getData")
    public ResponseEntity printData() {
        log.info("Recieved request at getData");
        String endpoint = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=RELIANCE.BSE&outputsize=full&apikey=demo";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<StockData> entity = new HttpEntity<StockData>(headers);
        StockData data = restTemplate.getForObject(endpoint, StockData.class);
        return ResponseEntity.ok().body(data);
    }
}
