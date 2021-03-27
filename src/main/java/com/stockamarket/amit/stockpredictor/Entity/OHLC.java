package com.stockamarket.amit.stockpredictor.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OHLC implements Serializable {
    @Expose
    @SerializedName("1. open")
    private double open;
    @Expose
    @SerializedName("2. high")
    private double high;
    @Expose
    @SerializedName("3. low")
    private double low;
    @Expose
    @SerializedName("4. close")
    private double close;
    @Expose
    @SerializedName("5. adjusted close")
    private double adjusted_close;
    @Expose
    @SerializedName("6. volume")
    private double volume;
    @Expose
    @SerializedName("7. dividend amount")
    private double dividend_amount;
    @Expose
    @SerializedName("8. split coefficient")
    private double split_coefficient;
}
