package com.stockamarket.amit.stockpredictor.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.stockamarket.amit.stockpredictor.Entity.OHLC;
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
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class BSEController {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/getData")
    public ResponseEntity printData() throws Exception {
        log.info("Recieved request at getData");
        String symbol = "INFY.BSE";
        String api = "QTET1VQSNTE48TKV";
        String endpoint = String.format("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=%s&outputsize=full&apikey=%s",symbol,api);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        try {
            Gson gson = new Gson();
            StockData data = gson.fromJson(restTemplate.exchange(endpoint, HttpMethod.GET, entity, String.class).getBody(), new TypeToken<StockData>() {
            }.getType());
            return ResponseEntity.ok().body(data);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e);
        }
    }
}
