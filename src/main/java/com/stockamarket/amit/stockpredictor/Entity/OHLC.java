package com.stockamarket.amit.stockpredictor.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OHLC {
    @JsonProperty("1. open")
    private String open;
    @JsonProperty("2. high")
    private String high;
    @JsonProperty("3. low")
    private String low;
    @JsonProperty("4. close")
    private String close;
    @JsonProperty("5. adjusted close")
    private String adjusted_close;
    @JsonProperty("6. volume")
    private String volume;
    @JsonProperty("7. dividend amount")
    private String divident_amount;
    @JsonProperty("8. split coefficient")
    private String split_coefficient;
}
