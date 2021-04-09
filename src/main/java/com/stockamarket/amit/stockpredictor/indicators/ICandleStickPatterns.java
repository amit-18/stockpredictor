package com.stockamarket.amit.stockpredictor.indicators;

import com.stockamarket.amit.stockpredictor.Entity.OHLC;
import com.stockamarket.amit.stockpredictor.Entity.StockData;
import com.stockamarket.amit.stockpredictor.Entity.Trade;

import java.util.List;

public interface ICandleStickPatterns {
    public Trade applyIndicator(List<OHLC> data);
}
