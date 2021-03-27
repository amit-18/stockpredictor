package com.stockamarket.amit.stockpredictor.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class StockData implements Serializable{
    @SerializedName("Meta Data")
    @Expose
    private MetaData metaData;
    @SerializedName(value = "Time Series (Daily)", alternate = {"Time Series (Weekly)","Time Series (Monthly)","Time Series (Intraday)"})
    @Expose
    private Map<String, OHLC> timeSeries;
}
