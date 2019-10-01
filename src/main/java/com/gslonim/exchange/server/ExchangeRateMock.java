package com.gslonim.exchange.server;

import com.google.common.collect.ImmutableMap;
import com.gslonim.exchange.api.Currency;
import com.gslonim.exchange.api.ExchangeRequest;

import java.util.Map;

public class ExchangeRateMock implements ExchangeRatesApi {

    @Override
    public Currency getRate(ExchangeRequest exchangeRequest) {
        return Currency.of(0.9183579759, "EUR");
    }

    @Override
    public HistoricalResult getHistoricalRate(HistoricalRequest historicalRequest) {
        return HistoricalResult.of(createMockResults());
    }

    private static Map<String, Currency> createMockResults() {
        return ImmutableMap.<String, Currency>builder()
                .put("2018-09-07", Currency.of(0.8609556608, "EUR"))
                .put("2018-09-04", Currency.of(0.864902266, "EUR"))
                .put("2018-09-05", Currency.of(0.8634087377, "EUR"))
                .put("2018-09-03", Currency.of(0.8614006374, "EUR"))
                .put("2018-09-06", Currency.of(0.859549596, "EUR"))
                .build();
    }
}
