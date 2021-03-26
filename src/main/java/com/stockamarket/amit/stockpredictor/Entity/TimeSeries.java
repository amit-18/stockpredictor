package com.stockamarket.amit.stockpredictor.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class TimeSeries {
    List<HashMap<String,OHLC>> timeSeriesData;
}
