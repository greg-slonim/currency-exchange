package com.gslonim.exchange.server;

import com.gslonim.exchange.api.Currency;
import com.gslonim.exchange.api.ExchangeRequest;

public interface ExchangeRatesApi {
    Currency getRate(ExchangeRequest exchangeRequest);
    HistoricalResult getHistoricalRate(HistoricalRequest historicalRequest);
}
