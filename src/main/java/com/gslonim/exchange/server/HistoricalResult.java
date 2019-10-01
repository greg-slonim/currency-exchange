package com.gslonim.exchange.server;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import com.gslonim.exchange.api.Currency;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;


@JsonDeserialize(builder = HistoricalResult.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class HistoricalResult {
    private final Map<String, Currency> rates;
    private volatile int memoizedHashCode;

    private HistoricalResult(Map<String, Currency> rates) {
        this.rates = Collections.unmodifiableMap(rates);
    }

    @JsonProperty("rates")
    public Map<String, Currency> getRates() {
        return this.rates;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof HistoricalResult && this.rates.equals(((HistoricalResult) other).rates));
    }

    @Override
    public int hashCode() {
        if (memoizedHashCode == 0) {
            memoizedHashCode = Objects.hash(rates);
        }
        return memoizedHashCode;
    }

    @Override
    public String toString() {
        return new StringBuilder("HistoricalResult")
                .append('{')
                .append("rates")
                .append(": ")
                .append(rates)
                .append('}')
                .toString();
    }

    public static HistoricalResult of(Map<String, Currency> rates) {
        return builder().rates(rates).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private Map<String, Currency> rates = new LinkedHashMap<>();

        private Builder() {}

        @JsonSetter(value = "rates", nulls = Nulls.SKIP)
        public Builder rates(Map<String, Currency> rates) {
            this.rates.clear();
            this.rates.putAll(Preconditions.checkNotNull(rates, "rates cannot be null"));
            return this;
        }

        public HistoricalResult build() {
            return new HistoricalResult(rates);
        }
    }
}
