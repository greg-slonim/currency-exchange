package com.gslonim.exchange.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;

import java.util.Objects;

@JsonDeserialize(builder = ExchangeResult.Builder.class)
public final class ExchangeResult {
    private final Currency value;
    private final String recommendation;
    private volatile int memoizedHashCode;

    private ExchangeResult(Currency value, String recommendation) {
        this.value = value;
        this.recommendation = recommendation;
    }

    @JsonProperty("value")
    public Currency getValue() {
        return this.value;
    }

    @JsonProperty("recommendation")
    public String getRecommendation() {
        return this.recommendation;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof ExchangeResult
                && this.value.equals(((ExchangeResult) other).value)
                && this.recommendation.equals(((ExchangeResult) other).recommendation));
    }

    @Override
    public int hashCode() {
        if (memoizedHashCode == 0) {
            memoizedHashCode = Objects.hash(value, recommendation);
        }
        return memoizedHashCode;
    }

    @Override
    public String toString() {
        return new StringBuilder("ExchangeResult")
                .append('{')
                .append("value")
                .append(": ")
                .append(value)
                .append(", ")
                .append("recommendation")
                .append(": ")
                .append(recommendation)
                .append('}')
                .toString();
    }

    public static ExchangeResult of(Currency value, String recommendation) {
        return builder().value(value).recommendation(recommendation).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static final class Builder {
        private Currency value;
        private String recommendation;

        private Builder() {}

        @JsonSetter("value")
        public Builder value(Currency value) {
            this.value = Preconditions.checkNotNull(value, "value cannot be null");
            return this;
        }

        @JsonSetter("recommendation")
        public Builder recommendation(String recommendation) {
            this.recommendation =
                    Preconditions.checkNotNull(recommendation, "recommendation cannot be null");
            return this;
        }

        public ExchangeResult build() {
            return new ExchangeResult(value, recommendation);
        }
    }
}
