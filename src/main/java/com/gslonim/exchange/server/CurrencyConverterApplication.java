package com.gslonim.exchange.server;

import com.gslonim.exchange.api.ForeignExchangeService;
import com.gslonim.exchange.health.ExchangeServiceHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class CurrencyConverterApplication extends Application<ConverterConfiguration> {
    public static void main(final String[] args) throws Exception {
        new CurrencyConverterApplication().run(args);
    }

    @Override
    public String getName() {
        return "Currency Converter (FOREX)";
    }

    @Override
    public void initialize(final Bootstrap<ConverterConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final ConverterConfiguration configuration,
                    final Environment environment) {
        //TODO: check configuration options
        Client client = ClientBuilder.newClient();
        ForeignExchangeService foreignExchangeService = new ForeignExchangeResource(new ExchangeRateIo(client));
        environment.healthChecks().register("exchange-service", new ExchangeServiceHealthCheck());
        environment.jersey().register(foreignExchangeService);
    }
}
