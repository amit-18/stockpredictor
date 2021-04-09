package com.stockamarket.amit.stockpredictor.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Trade {
    private String symbol;
    private boolean isBullish;
    private double stoploss;
    private double target;
    private String message;
}
