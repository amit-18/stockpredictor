package com.stockamarket.amit.stockpredictor.indicators;

import com.stockamarket.amit.stockpredictor.Entity.OHLC;
import com.stockamarket.amit.stockpredictor.Entity.Trade;

import java.util.List;

public class SpinningTop implements ICandleStickPatterns{

    @Override
    public Trade applyIndicator(List<OHLC> data) {
        Trade trade = new Trade();
        OHLC ohlc = data.get(0);
        double open = ohlc.getOpen();
        double low = ohlc.getLow();
        double high = ohlc.getHigh();
        double close = ohlc.getClose();
        if(Math.abs(open-close)<=4 && Math.abs(Math.abs(open-low)-Math.abs(high-close))<1.5){
            trade.setMessage("Uncertainty in Market");
        }
        return trade;
    }
}
