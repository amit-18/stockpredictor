package com.stockamarket.amit.stockpredictor.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import com.stockamarket.amit.stockpredictor.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.stockamarket.amit.stockpredictor.constants.Constants.*;

@Slf4j
@Service
public class Symbols {

    RestTemplate restTemplate = new RestTemplate();
    List<String> symbols = new ArrayList<>();
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity;
    public void createConnection() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36 Edg/89.0.774.63");
        headers.set("Host","www1.nseindia.com");
        headers.set("Connection","keep-alive");
        entity = new HttpEntity<String>(headers);
    }

    public List<String> getStockSymbols() {
        createConnection();
        String year_high = restTemplate.exchange(YEAR_HIGH_URL, HttpMethod.GET, entity, String.class).getBody();
        String top_gainers = restTemplate.exchange(GAINERS_URL, HttpMethod.GET, entity, String.class).getBody();
        String top_value = restTemplate.exchange(TOP_VALUE_URL, HttpMethod.GET, entity, String.class).getBody();
        String top_volume = restTemplate.exchange(TOP_VOLUME_URL, HttpMethod.GET, entity, String.class).getBody();
        extractFromJSON(year_high);
        extractFromJSON(top_gainers);
        extractFromJSON(top_value);
        extractFromJSON(top_volume);
        try {
            String line = "";
            String splitBy = ",";
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Sensex.csv"));
            while ((line = br.readLine()) != null) {
                String[] stock = line.split(splitBy);
                symbols.add(stock[2]);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return symbols.stream().distinct().collect(Collectors.toList());
    }

    public void extractFromJSON(String jsonStr) {
        JSONObject reader = new JSONObject(jsonStr);
        JSONArray jsonArray = reader.getJSONArray("data");
        IntStream.range(0,jsonArray.length()).mapToObj(index -> ((JSONObject)jsonArray
                .get(index)).optString("symbol"))
                .collect(Collectors.toCollection(() -> symbols));
    }
}
