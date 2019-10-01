package com.gslonim.exchange.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;

@JsonDeserialize(builder = Currency.Builder.class)
public final class Currency {
    private final double value;
    private final String currencySymbol;

    private Currency(double value, String currencySymbol) {
        this.value = value;
        this.currencySymbol = currencySymbol;
    }

    @JsonProperty("value")
    public double getValue() {
        return this.value;
    }

    @JsonProperty("currencySymbol")
    public String getCurrencySymbol() {
        return this.currencySymbol;
    }

    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof Currency && this.value == ((Currency) other).value && this.currencySymbol.equals(((Currency) other).currencySymbol));
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currencySymbol);
    }

    @Override
    public String toString() {
        return new StringBuilder("Currency")
                .append('{')
                .append("value")
                .append(": ")
                .append(currencySymbol)
                .append(value)
                .append('}')
                .toString();
    }

    public static Currency of(double value, String currencySymbol) {
        return builder().value(value).currencySymbol(currencySymbol).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private String currencySymbol;

        private double value;
        private Builder() {}
        @JsonSetter("value")
        public Builder value(double value) {
            this.value = value;
            return this;
        }

        @JsonSetter("currencySymbol")
        public Builder currencySymbol(String currencySymbol) {
            this.currencySymbol = currencySymbol;
            return this;
        }

        public Currency build() {
            return new Currency(value, currencySymbol);
        }
    }
}
