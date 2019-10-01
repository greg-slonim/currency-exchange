package com.gslonim.exchange.server;

import com.gslonim.exchange.api.Currency;
import com.gslonim.exchange.api.ExchangeRequest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class ExchangeRateIo implements ExchangeRatesApi {
    private static final String EXCHANGE_API_BASE_URL = "https://api.exchangeratesapi.io";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    private static String getRateUrl = EXCHANGE_API_BASE_URL + "/latest?base=%s&symbols=%s";
    private static String getHistoricalRatesUrl = EXCHANGE_API_BASE_URL
            + "/history?start_at=%s&end_at=%s&base=%s&symbols=%s";
    private final Client exchangeIoClient;

    public ExchangeRateIo(Client exchangeIo) {
        this.exchangeIoClient = exchangeIo;
    }

    @Override
    public Currency getRate(ExchangeRequest exchangeRequest) {
        WebTarget target = exchangeIoClient.target(
                String.format(getRateUrl, exchangeRequest.getBase(), exchangeRequest.getSymbol()));
        Rate rate = target.request(MediaType.APPLICATION_JSON_TYPE).get(Rate.class);
        return Currency.builder()
                .value(rate.getRates().getOrDefault(exchangeRequest.getSymbol(), 0.0))
                .currencySymbol(exchangeRequest.getSymbol())
                .build();
    }

    @Override
    public HistoricalResult getHistoricalRate(HistoricalRequest historicalRequest) {
        WebTarget target = exchangeIoClient.target(
                String.format(
                        getHistoricalRatesUrl,
                        formatDate(historicalRequest.getStartDate()),
                        formatDate(historicalRequest.getEndDate()),
                        historicalRequest.getBase(),
                        historicalRequest.getSymbol()));

        return target.request(MediaType.APPLICATION_JSON_TYPE).get(HistoricalResult.class);
    }

    private static String formatDate(OffsetDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }
}
