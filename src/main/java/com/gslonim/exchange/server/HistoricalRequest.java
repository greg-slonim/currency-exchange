package com.gslonim.exchange.server;

import com.google.common.base.Preconditions;

import java.time.OffsetDateTime;
import java.util.Objects;

public final class HistoricalRequest {
    private final OffsetDateTime startDate;
    private final OffsetDateTime endDate;
    private final String base;
    private final String symbol;
    private volatile int memoizedHashCode;

    private HistoricalRequest(
            OffsetDateTime startDate,
            OffsetDateTime endDate,
            String base,
            String symbol) {

        this.startDate = startDate;
        this.endDate = endDate;
        this.base = base;
        this.symbol = symbol;
    }

    public OffsetDateTime getStartDate() {
        return this.startDate;
    }
    public OffsetDateTime getEndDate() {
        return this.endDate;
    }
    public String getBase() {
        return this.base;
    }
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof HistoricalRequest
                && this.startDate.isEqual(((HistoricalRequest) other).startDate)
                && this.endDate.isEqual(((HistoricalRequest) other).endDate)
                && this.base.equals(((HistoricalRequest) other).base)
                && this.symbol.equals(((HistoricalRequest) other).symbol));
    }

    @Override
    public int hashCode() {
        if (memoizedHashCode == 0) {
            memoizedHashCode = Objects.hash(startDate.toInstant(), endDate.toInstant(), base, symbol);
        }
        return memoizedHashCode;
    }

    @Override
    public String toString() {
        return new StringBuilder("HistoricalRequest")
                .append('{')
                .append("startDate")
                .append(": ")
                .append(startDate)
                .append(", ")
                .append("endDate")
                .append(": ")
                .append(endDate)
                .append(", ")
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

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private OffsetDateTime startDate;
        private OffsetDateTime endDate;
        private String base;
        private String symbol;

        private Builder() {}

        public Builder startDate(OffsetDateTime startDate) {
            this.startDate = Preconditions.checkNotNull(startDate, "startDate cannot be null");
            return this;
        }

        public Builder endDate(OffsetDateTime endDate) {
            this.endDate = Preconditions.checkNotNull(endDate, "endDate cannot be null");
            return this;
        }

        public Builder base(String base) {
            this.base = Preconditions.checkNotNull(base, "base cannot be null");
            return this;
        }

        public Builder symbol(String symbol) {
            this.symbol = Preconditions.checkNotNull(symbol, "symbol cannot be null");
            return this;
        }

        public HistoricalRequest build() {
            return new HistoricalRequest(startDate, endDate, base, symbol);
        }
    }
}
