package com.stockamarket.amit.stockpredictor.indicators;

import com.stockamarket.amit.stockpredictor.Entity.OHLC;
import com.stockamarket.amit.stockpredictor.Entity.StockData;
import com.stockamarket.amit.stockpredictor.Entity.Trade;

import java.util.List;

public class Marubozu implements ICandleStickPatterns{

    @Override
    public Trade applyIndicator(List<OHLC> data) {
        Trade trade = new Trade();
        OHLC ohlc = data.get(0);
        double open = ohlc.getOpen();
        double low = ohlc.getLow();
        double high = ohlc.getHigh();
        double close = ohlc.getClose();
        trade.setBullish((open == low || (open - low) * 100 / open < 0.3) && (high == close || (high - close) * 100 / high < 0.3));
        trade.setStoploss(low);
        trade.setMessage("Check next day candle is blue. Avoid if the previous candle is very small or very Big.");
        return trade;
    }

}
