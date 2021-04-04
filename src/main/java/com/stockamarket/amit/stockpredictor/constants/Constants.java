package com.stockamarket.amit.stockpredictor.constants;

public class Constants {
    public static String TIME_SERIES_DAILY = "TIME_SERIES_DAILY";
    public static String TIME_SERIES_WEEKLY = "TIME_SERIES_WEEKLY";
    public static String TIME_SERIES_MONTHLY = "TIME_SERIES_MONTHLY";
    public static String TIME_SERIES_INTRADAY = "TIME_SERIES_INTRADAY";
    public static String MARKET_STATUS_URL = "https://www1.nseindia.com//emerge/homepage/smeNormalMktStatus.json";
    public static String INDICES_WATCH_URL = "https://www1.nseindia.com/live_market/dynaContent/live_watch/stock_watch/liveIndexWatchData.json";
    public static String SECTORS_LIST = "https://www1.nseindia.com/homepage/peDetails.json";
    public static String QUOTE_INFO_URL = "https://www1.nseindia.com/live_market/dynaContent/live_watch/get_quote/ajaxGetQuoteJSON.jsp?series=EQ&symbol=";
    public static String GET_QUOTE_URL = "https://www1.nseindia.com/live_market/dynaContent/live_watch/get_quote/GetQuote.jsp?symbol=";
    public static String GAINERS_URL = "https://www1.nseindia.com/live_market/dynaContent/live_analysis/gainers/niftyGainers1.json";
    public static String LOSERS_URL = "https://www1.nseindia.com/live_market/dynaContent/live_analysis/losers/niftyLosers1.json";
    public static String ADVANCES_DECLINES_URL = "https://www1.nseindia.com/common/json/indicesAdvanceDeclines.json";
    public static String INDEX_STOCKS_URL = "https://www1.nseindia.com/live_market/dynaContent/live_watch/stock_watch/";
    public static String INTRADAY_URL = "https://www1.nseindia.com/charts/webtame/tame_intraday_getQuote_closing_redgreen.jsp?Segment=CM&Series=EQ&CDExpiryMonth=&FOExpiryMonth=&IRFExpiryMonth=&CDDate1=&CDDate2=&Template=tame_intraday_getQuote_closing_redgreen.jsp&CDSymbol=";
    public static String SEARCH_URL = "https://www1.nseindia.com/live_market/dynaContent/live_watch/get_quote/ajaxCompanySearch.jsp?search=";
    public static String STOCK_OPTIONS_URL = "https://www1.nseindia.com/live_market/dynaContent/live_watch/get_quote/ajaxFOGetQuoteJSON.jsp";
    public static String YEAR_HIGH_URL = "https://www1.nseindia.com/products/dynaContent/equities/equities/json/online52NewHigh.json";
    public static String YEAR_LOW_URL = "https://www1.nseindia.com/products/dynaContent/equities/equities/json/online52NewLow.json";
    public static String TOP_VALUE_URL = "https://www1.nseindia.com/live_market/dynaContent/live_analysis/most_active/allTopValue1.json";
    public static String TOP_VOLUME_URL = "https://www1.nseindia.com/live_market/dynaContent/live_analysis/most_active/allTopVolume1.json";
    public static String NEW_CHART_DATA_URL = "https://www1.nseindia.com/ChartApp/install/charts/data/GetHistoricalNew.jsp";
    public static String API_KEY = "QTET1VQSNTE48TKV";
    public static String HISTORICAL_DATA_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=%s.BSE&outputsize=full&apikey=%s";
}
