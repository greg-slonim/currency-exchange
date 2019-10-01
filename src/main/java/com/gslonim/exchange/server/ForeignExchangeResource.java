package com.gslonim.exchange.server;

import com.gslonim.exchange.api.Currency;
import com.gslonim.exchange.api.ExchangeRequest;
import com.gslonim.exchange.api.ExchangeResult;
import com.gslonim.exchange.api.ForeignExchangeService;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.OptionalDouble;

public class ForeignExchangeResource implements ForeignExchangeService {
    private static final String EXCHANGE_RECOMMENDATION = "We recommend to exchange.";
    private static final String HOLD_RECOMMENDATION = "We recommend to wait with your exchange.";
    private static final String NO_RECOMMENDATION = "We cannot issue recommendation at this time" +
            "due to absence of historical results.";

    private final ExchangeRatesApi exchangeServiceClient;

    ForeignExchangeResource(ExchangeRatesApi exchangeRatesApi) {
        this.exchangeServiceClient = exchangeRatesApi;
    }

    @Override
    public ExchangeResult getExchangeAndRecommendations(ExchangeRequest request) {
        Currency rate = exchangeServiceClient.getRate(request);
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        OffsetDateTime start = now.minusDays(7);

        HistoricalResult historicalResult = exchangeServiceClient.getHistoricalRate(
                HistoricalRequest.builder()
                        .base(request.getBase())
                        .symbol(request.getSymbol())
                        .startDate(start)
                        .endDate(now)
                        .build());

        // SUPER naive! Need trends!
        OptionalDouble maybeRateAverage = historicalResult.getRates().values().stream()
                .mapToDouble(Currency::getValue)
                .average();

        ExchangeResult.Builder builder = ExchangeResult.builder().value(rate);
        if (!maybeRateAverage.isPresent()) {
            return builder.recommendation(NO_RECOMMENDATION).build();
        }
        if (maybeRateAverage.getAsDouble() < rate.getValue()) {
            return builder.recommendation(EXCHANGE_RECOMMENDATION).build();
        }

        return builder.recommendation(HOLD_RECOMMENDATION).build();
    }
}
