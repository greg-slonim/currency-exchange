package com.gslonim.exchange.health;

import com.codahale.metrics.health.HealthCheck;

public class ExchangeServiceHealthCheck extends HealthCheck {

    //TODO: Proper implementation
    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
