package com.gslonim.exchange.server;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@JsonDeserialize(builder = Rate.Builder.class)
public final class Rate {
    private final Map<String, Double> rates;
    private final String base;
    //This would be better with OffsetDateTime and a custom deserializer but no time for that.
    private final String date;
    private volatile int memoizedHashCode;

    private Rate(Map<String, Double> rates, String base, String date) {
        this.rates = Collections.unmodifiableMap(rates);
        this.base = base;
        this.date = date;
    }

    @JsonProperty("rates")
    public Map<String, Double> getRates() {
        return this.rates;
    }

    @JsonProperty("base")
    public String getBase() {
        return this.base;
    }

    @JsonProperty("date")
    public String getDate() {
        return this.date;
    }

    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof Rate && this.rates.equals(((Rate) other).rates)
                && this.base.equals(((Rate) other).base)
                && this.date.equals(((Rate) other).date));
    }

    @Override
    public int hashCode() {
        if (memoizedHashCode == 0) {
            memoizedHashCode = Objects.hash(rates, base, date);
        }
        return memoizedHashCode;
    }

    @Override
    public String toString() {
        return new StringBuilder("Rate")
                .append('{')
                .append("rates")
                .append(": ")
                .append(rates)
                .append(", ")
                .append("base")
                .append(": ")
                .append(base)
                .append(", ")
                .append("date")
                .append(": ")
                .append(date)
                .append('}')
                .toString();
    }

    public static Rate of(Map<String, Double> rates, String base, String date) {
        return builder().rates(rates).base(base).date(date).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private Map<String, Double> rates = new LinkedHashMap<>();

        private String base;

        private String date;

        private Builder() {}

        public Builder from(Rate other) {
            rates(other.getRates());
            base(other.getBase());
            date(other.getDate());
            return this;
        }

        @JsonSetter(value = "rates", nulls = Nulls.SKIP)
        public Builder rates(Map<String, Double> rates) {
            this.rates.clear();
            this.rates.putAll(Preconditions.checkNotNull(rates, "rates cannot be null"));
            return this;
        }

        public Builder rates(String key, double value) {
            this.rates.put(key, value);
            return this;
        }

        @JsonSetter("base")
        public Builder base(String base) {
            this.base = Preconditions.checkNotNull(base, "base cannot be null");
            return this;
        }

        @JsonSetter("date")
        public Builder date(String date) {
            this.date = Preconditions.checkNotNull(date, "date cannot be null");
            return this;
        }

        public Rate build() {
            return new Rate(rates, base, date);
        }
    }
}
