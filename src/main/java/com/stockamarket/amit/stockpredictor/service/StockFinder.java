package com.stockamarket.amit.stockpredictor.service;

import com.google.gson.Gson;
import com.stockamarket.amit.stockpredictor.Entity.OHLC;
import com.stockamarket.amit.stockpredictor.Entity.StockData;
import com.stockamarket.amit.stockpredictor.Entity.Trade;
import com.stockamarket.amit.stockpredictor.indicators.Marubozu;
import com.stockamarket.amit.stockpredictor.indicators.SpinningTop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ta4j.core.*;
import org.ta4j.core.indicators.*;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.helpers.PriceVariationIndicator;
import org.ta4j.core.indicators.helpers.TypicalPriceIndicator;
import org.ta4j.core.indicators.statistics.StandardDeviationIndicator;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.*;

@Slf4j
@Service
public class StockFinder {
    Marubozu marubozu = new Marubozu();
    SpinningTop spinningTop = new SpinningTop();
    public BarSeries createBarSeries() throws FileNotFoundException {
        Gson gson = new Gson();
//        StockData data = gson.fromJson(, new TypeToken<StockData>() {
//            }.getType());
        BufferedReader br = new BufferedReader(new FileReader("src/main/data/AGCNET.json"));
        StockData data = gson.fromJson(br, StockData.class);
        BarSeries series = new BaseBarSeries();
        ZonedDateTime endTime = ZonedDateTime.now();
        long i =0;
        List<OHLC> ohlc = new ArrayList<>(data.getTimeSeries().values());
        singleCandleStickPatternFinder(ohlc);
        Collections.reverse(ohlc);
        for (OHLC object : ohlc) {
            series.addBar(endTime.plusDays(i), object.getOpen(), object.getHigh(), object.getLow(), object.getClose(), object.getVolume());
            i++;
        }
        return series;
    }

    public void singleCandleStickPatternFinder(List<OHLC> data){
        Trade traderesult1 = marubozu.applyIndicator(data);
        Trade traderesult2 = spinningTop.applyIndicator(data);
    }


    public void runBarSeries() throws FileNotFoundException {
        BarSeries series = createBarSeries();
        ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
//// Getting the simple moving average (SMA) of the close price over the last 5 ticks
//        SMAIndicator shortSma = new SMAIndicator(closePrice, 10);
//
//// Getting a longer SMA (e.g. over the 30 last ticks)
//        SMAIndicator longSma = new SMAIndicator(closePrice, 20);
//        Rule buyingRule = new CrossedUpIndicatorRule(shortSma, longSma);
//        Rule sellingRule = new CrossedDownIndicatorRule(shortSma, longSma)
//                .or(new StopLossRule(closePrice, 3.0))
//                .or(new StopGainRule(closePrice, 2.0));
//
//        Strategy strategy = new BaseStrategy(buyingRule, sellingRule);
//        BarSeriesManager manager = new BarSeriesManager(series);
//        TradingRecord tradingRecord = manager.run(strategy);
//        log.info("Number of trades for our strategy: " + tradingRecord.getTradeCount());
//        // Getting the profitable trades ratio
//        AnalysisCriterion profitTradesRatio = new AverageProfitableTradesCriterion();
//        log.info("Profitable trades ratio: " + profitTradesRatio.calculate(series, tradingRecord));
        // Getting the reward-risk ratio
//        AnalysisCriterion rewardRiskRatio = new RewardRiskRatioCriterion();
//        log.info("Reward-risk ratio: " + rewardRiskRatio.calculate(series, tradingRecord));
//
//        // Total profit of our strategy
//        // vs total profit of a buy-and-hold strategy
//        AnalysisCriterion vsBuyAndHold = new VersusBuyAndHoldCriterion(new TotalProfitCriterion());
//        log.info("Our profit vs buy-and-hold profit: " + vsBuyAndHold.calculate(series, tradingRecord));
        // Typical price
        TypicalPriceIndicator typicalPrice = new TypicalPriceIndicator(series);
        // Price variation
        PriceVariationIndicator priceVariation = new PriceVariationIndicator(series);
        // Simple moving averages
        SMAIndicator shortSma = new SMAIndicator(closePrice, 9);
        SMAIndicator longSma = new SMAIndicator(closePrice, 20);
        // Exponential moving averages
        EMAIndicator shortEma = new EMAIndicator(closePrice, 9);
        EMAIndicator longEma = new EMAIndicator(closePrice, 20);
        // Percentage price oscillator
        PPOIndicator ppo = new PPOIndicator(closePrice, 12, 26);
        // Rate of change
        ROCIndicator roc = new ROCIndicator(closePrice, 100);
        // Relative strength index
        RSIIndicator rsi = new RSIIndicator(closePrice, 14);
        // Williams %R
        WilliamsRIndicator williamsR = new WilliamsRIndicator(series, 20);
        // Average true range
        ATRIndicator atr = new ATRIndicator(series, 20);
        // Standard deviation
        StandardDeviationIndicator sd = new StandardDeviationIndicator(closePrice, 14);
        StringBuilder sb = new StringBuilder(
                "timestamp,close,typical,variation,sma8,sma20,ema8,ema20,ppo,roc,rsi,williamsr,atr,sd\n");

        /*
         * Adding indicators values
         */
        final int nbBars = series.getBarCount();
        for (int i = 0; i < nbBars; i++) {
            sb.append(series.getBar(i).getEndTime()).append(',').append(closePrice.getValue(i)).append(',')
                    .append(typicalPrice.getValue(i)).append(',').append(priceVariation.getValue(i)).append(',')
                    .append(shortSma.getValue(i)).append(',').append(longSma.getValue(i)).append(',')
                    .append(shortEma.getValue(i)).append(',').append(longEma.getValue(i)).append(',')
                    .append(ppo.getValue(i)).append(',').append(roc.getValue(i)).append(',').append(rsi.getValue(i))
                    .append(',').append(williamsR.getValue(i)).append(',').append(atr.getValue(i)).append(',')
                    .append(sd.getValue(i)).append('\n');
        }

        /*
         * Writing CSV file
         */
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File("target", "indicators.csv")));
            writer.write(sb.toString());
        } catch (IOException ioe) {
            log.info("Unable to write CSV file", ioe);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

}
