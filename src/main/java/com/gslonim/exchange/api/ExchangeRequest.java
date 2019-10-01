package com.gslonim.exchange.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;

import java.util.Objects;

@JsonDeserialize(builder = ExchangeRequest.Builder.class)
public final class ExchangeRequest {
    private final String base;
    private final String symbol;
    private volatile int memoizedHashCode;

    private ExchangeRequest(String base, String symbol) {
        this.base = base;
        this.symbol = symbol;
    }

    @JsonProperty("base")
    public String getBase() {
        return this.base;
    }

    @JsonProperty("symbol")
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof ExchangeRequest
                && this.base.equals(((ExchangeRequest) other).base)
                && this.symbol.equals(((ExchangeRequest) other).symbol));
    }

    @Override
    public int hashCode() {
        if (memoizedHashCode == 0) {
            memoizedHashCode = Objects.hash(base, symbol);
        }
        return memoizedHashCode;
    }

    @Override
    public String toString() {
        return new StringBuilder("ExchangeRequest")
                .append('{')
                .append("base")
                .append(": ")
                .append(base)
                .append(", ")
                .append("symbol")
                .append(": ")
                .append(symbol)
                .append('}')
                .toString();
    }

    public static ExchangeRequest of(String base, String symbol) {
        return builder().base(base).symbol(symbol).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private String base;
        private String symbol;

        private Builder() {}

        @JsonSetter("base")
        public Builder base(String base) {
            this.base = Preconditions.checkNotNull(base, "base cannot be null");
            return this;
        }

        @JsonSetter("symbol")
        public Builder symbol(String symbol) {
            this.symbol = Preconditions.checkNotNull(symbol, "symbol cannot be null");
            return this;
        }
        public ExchangeRequest build() {
            return new ExchangeRequest(base, symbol);
        }
    }
}
