package com.stockamarket.amit.stockpredictor.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
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
    public ResponseEntity printData() throws Exception {
        log.info("Recieved request at getData");
        String endpoint = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=RELIANCE.BSE&outputsize=full&apikey=demo";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        try {
            Gson gson = new Gson();
//            String body = restTemplate.exchange(endpoint, HttpMethod.GET, entity, String.class).getBody();
            StockData data = gson.fromJson(restTemplate.exchange(endpoint, HttpMethod.GET, entity, String.class).getBody(), new TypeToken<StockData>() {
            }.getType());
//            StockData dataa = gson.fromJson(restTemplate.getForEntity(endpoint, String.class).getBody(),new TypeToken<StockData>() {
//            }.getType());
            return ResponseEntity.ok().body(data);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e);
        }
    }
}
